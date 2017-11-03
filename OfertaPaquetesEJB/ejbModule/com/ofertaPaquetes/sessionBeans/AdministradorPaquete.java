package com.ofertaPaquetes.sessionBeans;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;
import com.ofertaPaquetes.dtos.PaqueteServicioDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Destino;
import com.ofertaPaquetes.entities.Imagen;
import com.ofertaPaquetes.entities.Paquete;
import com.ofertaPaquetes.entities.Provincia;
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

    @Resource(lookup = "java:/myJmsTest/MyQueue")
	// dana @Resource(lookup = "java:/jms/queue/testQueue")
    Destination destination;

	public AdministradorPaquete() {
        // TODO Auto-generated constructor stub
    }
	
	public void nuevoPaquete(PaqueteDTO paquete){
		try{
			Paquete paq = new Paquete(paquete.getNombre(),paquete.getFechaDesde(), paquete.getFechaHasta(),
					paquete.getDescripcion(), paquete.getPrecio(), paquete.getPoliticasCancelacion(), 
					paquete.getCupo(), paquete.getCantPersonas(), paquete.isEstado(), paquete.isNuevaOferta());
			
	
						
			//Servicios
			
			
			/*La agencia ya existe*/
			Agencia agencia = manager.find(Agencia.class, paquete.getAgencia().getIdAgencia());
			paq.setAgencia(agencia);
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
			System.out.println("eeeeeeeeeee");
			manager.persist(paq);
			System.out.println("eeeeeeeeeee2");
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

			
			/*Servicios*/
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
		
			
			manager.merge(paq);
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
			
			/*Imagenes*/
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
        	QueueConnection connection = (QueueConnection) connectionFactory.createConnection("vanesa","Vanesa14");

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
		
	   	JsonObject paqueteJson = Json.createObjectBuilder()
   			.add("codigo_prestador",paquete.getAgencia().getIdAgencia())/*TODO: poner el id de la agencia del BO*/
   					.add("destino",paquete.getDestino().getNombre())
   					.add("fecha_desde",getFechaString(paquete.getFechaDesde()))
   					.add("fecha_hasta",getFechaString(paquete.getFechaHasta()))
   					.add("cantidad_personas_paquete",paquete.getCantPersonas())
   					.add("foto_paquete",paquete.getImagen()) /*Poner una sola imagen, con la URL*/
   					.add("descripcion_paquete",paquete.getDescripcion())
   					.add("precio", 8987)
   					.add("latitud",12)
   					.add("longitud",23)
   					.add("politica_cancelacion",paquete.getPoliticasCancelacion())
   					.add("mail_agencia","pepeq@pepep.com")
   					.add("cupo_paquete", 44)
   					.add("servicios_paquete", 
   		                     Json.createArrayBuilder().add(paquete.getServicios().get(0).getNombreServicio())
   		                                              .add(paquete.getServicios().get(1).getNombreServicio())
   		                                              .build())
   					.build();
   					
        StringWriter stringWriter = new StringWriter();
        
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(paqueteJson);
        writer.close();
        
        return paqueteJson.toString();
    }

	private String getFechaString(Date fecha) {
		
		String date = "";
		date +=  fecha.getYear() + fecha.getMonth() + fecha.getDay();
		
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
	
}

