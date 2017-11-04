package com.ofertaPaquetes.sessionBeans;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.MedioDePagoDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;
import com.ofertaPaquetes.dtos.PaqueteServicioDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Destino;
import com.ofertaPaquetes.entities.Imagen;
import com.ofertaPaquetes.entities.MedioDePago;
import com.ofertaPaquetes.entities.Pais;
import com.ofertaPaquetes.entities.Paquete;
import com.ofertaPaquetes.entities.PaqueteServicio;
import com.ofertaPaquetes.entities.Provincia;
import com.ofertaPaquetes.util.Properties;

/**
 * Session Bean implementation class AdministradorTareas
 */
@Stateless
@LocalBean
public class AdministradorPaquete{
	
	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;

	@Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = Properties.DESTINATION)
    Destination destination;

	public AdministradorPaquete() {
        // TODO Auto-generated constructor stub
    }
	
	public void nuevoPaquete(PaqueteDTO paquete){
		try{
			Paquete paq = new Paquete(paquete.getNombre(),paquete.getFechaDesde(), paquete.getFechaHasta(),
					paquete.getDescripcion(), paquete.getPrecio(), paquete.getPoliticasCancelacion(), 
					paquete.getCupo(), paquete.getCantPersonas(), paquete.isEstado(), paquete.isNuevaOferta());
			
			/*La agencia ya existe*/
			Agencia agencia = manager.find(Agencia.class, paquete.getAgencia().getIdAgencia());
			paq.setAgencia(agencia);
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
			
			/*Medios de Pago*/
			List<MedioDePago> lmediosDePago = new ArrayList<MedioDePago>();
			if(paquete.getMediosDePago()!=null){
				for(MedioDePagoDTO mp: paquete.getMediosDePago()){
					lmediosDePago.add(manager.find(MedioDePago.class,mp.getIdMedioDePago()));
				}
			paq.setMediosDePago(lmediosDePago);
			}
			
			manager.persist(paq);
			manager.flush();
			
			/*Servicios*/
			if(paquete.getServicios()!=null){
				for(PaqueteServicioDTO ps:paquete.getServicios()){
					PaqueteServicio paqserv = new PaqueteServicio(ps.getIdServicio(), ps.getNombreServicio());
					paqserv.setIdPaquete(paq.getIdPaquete());
					manager.persist(paqserv);
				}
			}
			
			sendToPortalWeb(paquete);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al crear paquete");
		}
	}
	
	public void modificarPaquete(PaqueteDTO paquete){
		try{
			Paquete paq = manager.find(Paquete.class,paquete.getIdPaquete());
			paq.setAgencia(manager.find(Agencia.class,paquete.getAgencia().getIdAgencia()));
			paq.setCantPersonas(paquete.getCantPersonas());
			paq.setCupo(paquete.getCupo());
			paq.setDescripcion(paquete.getDescripcion());
			paq.setEstado(paquete.isEstado());
			paq.setFechaDesde(paquete.getFechaDesde());
			paq.setFechaHasta(paquete.getFechaHasta());
			paq.setNombre(paquete.getNombre());
			paq.setPoliticasCancelacion(paquete.getPoliticasCancelacion());
			paq.setPrecio(paquete.getPrecio());
			paq.setImagen(paquete.getImagen());

			
			
			/*Medios de Pago*/
			List<MedioDePago> lmediosDePago = new ArrayList<MedioDePago>();
			if(paquete.getMediosDePago()!=null){
				for(MedioDePagoDTO mp: paquete.getMediosDePago()){
					lmediosDePago.add(manager.find(MedioDePago.class,mp.getIdMedioDePago()));
				}
			paq.setMediosDePago(lmediosDePago);
			}
			
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
		
			
			manager.merge(paq);
			manager.flush();
			
			/*Servicios*/
			if(paquete.getServicios()!=null){
				for(PaqueteServicioDTO ps:paquete.getServicios()){
					PaqueteServicio paqserv = new PaqueteServicio(ps.getIdServicio(), ps.getNombreServicio());
					paqserv.setIdPaquete(paq.getIdPaquete());
					manager.persist(paqserv);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al actualizar Paquete");
		}
	}
	
	public void eliminarPaquete(int idPaquete){
		try{
			Paquete paquete = manager.find(Paquete.class, idPaquete);
			manager.remove(paquete);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al eliminar Paquete");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PaqueteDTO> listarPaquetes(){
		try{
			List<PaqueteDTO> lista = new ArrayList<PaqueteDTO>();
		
			List<Paquete> paquetes = (List<Paquete>) manager.createQuery(" FROM Paquete").getResultList();
		
			for(Paquete paq:paquetes){
				PaqueteDTO dto = new PaqueteDTO(paq.getNombre(),paq.getFechaDesde(),paq.getFechaHasta(),paq.getDescripcion(),paq.getPrecio(),paq.getPoliticasCancelacion(),paq.getCupo(),paq.getCantPersonas(),paq.isEstado(),paq.isNuevaOferta());
				dto.setIdPaquete(paq.getIdPaquete());
				PaisDTO pais = new PaisDTO(paq.getAgencia().getPais().getIdPais(), paq.getAgencia().getPais().getNombre());
				AgenciaDTO agencia = new AgenciaDTO(paq.getAgencia().getNombre(), paq.getAgencia().isEstado(),paq.getAgencia().getCalle(),paq.getAgencia().getNro(),paq.getAgencia().getPiso(),paq.getAgencia().getDepto(),paq.getAgencia().getLocalidad(),pais, paq.getAgencia().getFechaCreacion());
				
				if(paq.getDestino()!=null){
					DestinoDTO destino = new DestinoDTO(paq.getDestino().getIdDestino(),paq.getDestino().getNombre());
					dto.setDestino(destino);
				}
				
				dto.setAgencia(agencia);
				
				/*no cargo imagenes y tipo de servicios ya que esto es solo una lista*/
				
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public PaqueteDTO obtenerPaquete(int idPaquete){
		try{
			Paquete paquete = manager.find(Paquete.class, idPaquete);
			PaqueteDTO paq = new PaqueteDTO(paquete.getNombre(),paquete.getFechaDesde(),paquete.getFechaHasta(),paquete.getDescripcion(),paquete.getPrecio(),paquete.getPoliticasCancelacion(),paquete.getCupo(),paquete.getCantPersonas(),paquete.isEstado(),paquete.isNuevaOferta());
			System.out.println(paquete.toString());
			
			AgenciaDTO agencia = new AgenciaDTO(paquete.getAgencia().getIdAgencia());
			paq.setAgencia(agencia);
			
			if(paquete.getDestino()!=null){
				DestinoDTO destino = new DestinoDTO(paquete.getDestino().getIdDestino(),paquete.getDestino().getNombre());
				paq.setDestino(destino);
			}

			paq.setImagen(paquete.getImagen());

			/*Servicios*/
			return paq;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al obtener paquete");
		}
		return null;
	}


	/** Enviar Paquete a BO - JMS **/
	private void sendToPortalWeb(PaqueteDTO paquete) {

        try {
            //Authentication info can be omitted if we are using in-vm
            // dana QueueConnection connection = (QueueConnection) connectionFactory.createConnection("myUser","myPassword");
        	QueueConnection connection = (QueueConnection) connectionFactory.createConnection(Properties.CONNECTION_FACTORY_USER,Properties.CONNECTION_FACTORY_PASSWORD);

            try {
                QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

                try {
                    MessageProducer producer = session.createProducer(destination);

                    try {
                   	
                    	String jsonPaquete = getJsonPaquete(paquete);
                    	System.out.println("jsonPaquete");
                    	System.out.println(jsonPaquete);              		                    		
                		                    	
                    	TextMessage message = session.createTextMessage(jsonPaquete);
                    	
                        producer.send(message);

                        System.out.println("Message sent! ^__^");
                    }
                    catch(Exception ex){
                    	System.out.println(ex.getMessage());                    	
                    	ex.printStackTrace(System.out);
                    	throw ex;
                    } finally {
                        producer.close();
                    }
                } finally {
                    session.close();
                }

            } finally {
                connection.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

	}

	//http://www.java2s.com/Tutorials/Java/JSON/0100__JSON_Java.htm
	private String getJsonPaquete(PaqueteDTO paquete){
         
		System.out.println("jsonPaquete algo algo");
		
	   	JsonObjectBuilder paqueteJsonBuilder = Json.createObjectBuilder()
   			.add("codigo_prestador",paquete.getAgencia().getIdAgencia())/*TODO: poner el id de la agencia del BO*/
   					.add("destino",paquete.getDestino().getNombre())
   					.add("fecha_desde",getFechaString(paquete.getFechaDesde()))
   					.add("fecha_hasta",getFechaString(paquete.getFechaHasta()))
   					.add("cantidad_personas_paquete",paquete.getCantPersonas())
   					.add("foto_paquete",paquete.getImagen()) /*Poner una sola imagen, con la URL*/
   					.add("descripcion_paquete",paquete.getDescripcion())
   					.add("precio", paquete.getPrecio())
   					.add("latitud",paquete.getLatitud())
   					.add("longitud",paquete.getLongitud())
   					.add("politica_cancelacion",paquete.getPoliticasCancelacion())
   					.add("mail_agencia",paquete.getAgencia().getEmail())
   					.add("cupo_paquete", paquete.getCupo());//.build();
	   	
	   	for(int i=0; i < paquete.getServicios().size(); i++){
			paqueteJsonBuilder.add("servicios_paquete", 
	                     Json.createArrayBuilder()
	                     .add(paquete.getServicios().get(i).getNombreServicio()));
		
	   	}
   					
	   	JsonObject paqueteJson = paqueteJsonBuilder.build();
        StringWriter stringWriter = new StringWriter();
        
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(paqueteJson);
        writer.close();
        
        return paqueteJson.toString();
    }

	private String getFechaString(Date fecha) {
		
		String date = "";
		String month = "";
		String day = "";
		if(fecha.getMonth() < 10)
			month = "0"+fecha.getMonth();
		if(fecha.getDay() < 10)
			month = "0"+fecha.getDay();
		
		date +=  fecha.getYear() + month + day;
		
		return date;
    }

	public List<ProvinciaDTO> getListadoProvincias(){
		
		try{
			List<ProvinciaDTO> lista = new ArrayList<ProvinciaDTO>();
			
			List<Provincia> provincias = (List<Provincia>) manager.createQuery(" FROM Provincia").getResultList();
		
			for(Provincia prov:provincias){
				ProvinciaDTO dto = new ProvinciaDTO(prov.getIdProvincia(),prov.getNombre());
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("No se pudo traer listado de provincias");
		}
		return null;
	}
	
	public List<MedioDePagoDTO> getListadoMediosDePago(){
		
		try{
			List<MedioDePagoDTO> lista = new ArrayList<MedioDePagoDTO>();
			
			List<MedioDePago> medios = (List<MedioDePago>) manager.createQuery(" FROM MedioDePago").getResultList();
		
			for(MedioDePago md:medios){
				MedioDePagoDTO dto = new MedioDePagoDTO(md.getIdMedioDePago(),md.getNombre());
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("No se pudo traer listado medios de pago");
		}
		return null;
	}
	
	
	public void cargarDatosIniciales(){
		try{

			// Medios de Pago
			List<MedioDePago> listaMP = new ArrayList<MedioDePago>();
			listaMP.add(new MedioDePago(1,"Tarjeta"));
			listaMP.add(new MedioDePago(2,"Cheque"));
			listaMP.add(new MedioDePago(3,"Pago en Destino"));
			listaMP.add(new MedioDePago(4,"Mercado Pago"));
			listaMP.add(new MedioDePago(5,"Pay Pal"));
			
			for(MedioDePago mp:listaMP){
				System.out.println(mp.toString());
				manager.persist(mp);
			}
			
			//Provincias
			List<Provincia> listaProv = new ArrayList<Provincia>();
			listaProv.add(new Provincia(1,"Buenos Aires"));
			listaProv.add(new Provincia(2,"Ciudad Autonoma de Buenos Aires"));
			listaProv.add(new Provincia(3,"Chaco"));
			listaProv.add(new Provincia(4,"Catamarca"));
			listaProv.add(new Provincia(5,"Chubut"));
			listaProv.add(new Provincia(6,"Cordoba"));
			listaProv.add(new Provincia(7,"Corrientes"));
			listaProv.add(new Provincia(8,"Entre Rios"));
			listaProv.add(new Provincia(9,"Formosa"));
			listaProv.add(new Provincia(10,"Jujuy"));
			listaProv.add(new Provincia(12,"La Pampa"));
			listaProv.add(new Provincia(13,"La Rioja"));
			listaProv.add(new Provincia(14,"Mendoza"));
			listaProv.add(new Provincia(15,"Misiones"));
			listaProv.add(new Provincia(16,"Neuquen"));
			listaProv.add(new Provincia(17,"Rio Negro"));
			listaProv.add(new Provincia(18,"Salta"));
			listaProv.add(new Provincia(19,"San Juan"));
			listaProv.add(new Provincia(20,"San Luis"));
			listaProv.add(new Provincia(21,"Santa Cruz"));
			listaProv.add(new Provincia(22,"Santa Fe"));
			listaProv.add(new Provincia(23,"Santiago Del Estero"));
			listaProv.add(new Provincia(24,"Tucuman"));
			
			for(Provincia prov:listaProv){
				manager.persist(prov);
			}
			
			//Pais
			manager.persist(new Pais(1,"Argentina"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

