package com.ofertaPaquetes.sessionBeans;


import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.hornetq.utils.json.JSONObject;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Pais;
import com.ofertaPaquetes.entities.Paquete;
import com.ofertaPaquetes.entities.Provincia;
import com.ofertaPaquetes.util.Properties;

/**
 * Session Bean implementation class AdministradorTareas
 */
@Stateless
@LocalBean
public class AdministradorAgencia {

	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;
    
	public AdministradorAgencia() {
        // TODO Auto-generated constructor stub
    }
	
	public void nuevaAgencia(AgenciaDTO agenciaDto){
		
		Pais pais =manager.find(Pais.class, agenciaDto.getPais().getIdPais());
		
		Agencia agencia = new Agencia(agenciaDto.getNombre(),false,agenciaDto.getCalle(), agenciaDto.getNro(),agenciaDto.getPiso(),
				agenciaDto.getDepto(), agenciaDto.getLocalidad(),pais,agenciaDto.getFechaCreacion());
		
		agencia.setEmail(agenciaDto.getEmail());
		
		try{
			Provincia provincia = manager.find(Provincia.class, agenciaDto.getProvincia().getIdProvincia());
			agencia.setProvincia(provincia);
						
			int idAgenciaBO = postAgencias(agenciaDto);
			agencia.setIdAgenciaBO(idAgenciaBO);

			manager.persist(agencia);
			
			AdministradorLogs log = new AdministradorLogs();					
			log.enviarLog("Oferta Paquetes", "Oferta Paquetes","Crear Agencia", "Creacion Exitosa");
			
		}
		catch(Exception e){
			System.out.println("--------------Error al guardar Agencia------------");
			e.printStackTrace();
			
			AdministradorLogs log = new AdministradorLogs();					
			log.enviarLog("Oferta Paquetes", "Oferta Paquetes","Crear Agencia", "Creacion No Exitosa");
		}
	}
	
	public void modificarAgencia(AgenciaDTO ag){
		try{
			Agencia agencia = manager.find(Agencia.class,ag.getIdAgencia());
			agencia.setCalle(ag.getCalle());
			agencia.setEmail(ag.getEmail());
			agencia.setDepto(ag.getDepto());
			agencia.setEstado(ag.isEstado());
			agencia.setLocalidad(ag.getLocalidad());
			agencia.setNombre(ag.getNombre());
			agencia.setNro(ag.getNro());
			agencia.setPiso(ag.getPiso());
			
			Pais pais = manager.find(Pais.class, ag.getPais().getIdPais());
			agencia.setPais(pais);
			
			Provincia prov = manager.find(Provincia.class, ag.getProvincia().getIdProvincia());
			agencia.setProvincia(prov);
			
			manager.merge(agencia);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al actualizar Agencia");
		}
	}
	
	public void eliminarAgencia(int idAgencia){
		try{
			Agencia agencia = manager.find(Agencia.class, idAgencia);
			manager.remove(agencia);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al eliminar Agencia");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AgenciaDTO> listarAgenciasPorEstado(boolean estado){

		try{
			List<AgenciaDTO> lista = new ArrayList<AgenciaDTO>();
		
			List<Agencia> agencias = (List<Agencia>) manager.createQuery(" FROM Agencia  a "
				+ "WHERE a.estado=:estado").setParameter("estado",estado)
				.getResultList();
		
			for(Agencia ag:agencias){
				
				PaisDTO pais = new PaisDTO(ag.getPais().getNombre());
				pais.setIdPais(ag.getPais().getIdPais());

				AgenciaDTO dto= new AgenciaDTO(ag.getNombre(),ag.isEstado(),ag.getCalle(),ag.getNro(),ag.getPiso(),ag.getDepto(),ag.getLocalidad(),pais, ag.getFechaCreacion());
				dto.setIdAgencia(ag.getIdAgencia());
				
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AgenciaDTO> listarAgencias(){
		try{
			List<AgenciaDTO> lista = new ArrayList<AgenciaDTO>();
		
			List<Agencia> agencias = (List<Agencia>) manager.createQuery(" FROM Agencia  a ").getResultList();
		
			for(Agencia ag:agencias){
				
				PaisDTO pais = new PaisDTO(ag.getPais().getNombre());
				pais.setIdPais(ag.getPais().getIdPais());
				
				ProvinciaDTO prov = new ProvinciaDTO();
				prov.setIdProvincia(ag.getProvincia().getIdProvincia());
				prov.setNombre(ag.getProvincia().getNombre());
				
				AgenciaDTO dto= new AgenciaDTO(ag.getNombre(),ag.isEstado(),ag.getCalle(),ag.getNro(),ag.getPiso(),ag.getDepto(),ag.getLocalidad(),pais,ag.getFechaCreacion());
				dto.setIdAgencia(ag.getIdAgencia());
				dto.setProvincia(prov);
				
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public AgenciaDTO obtenerAgencia(int idAgencia){
		try{
			Agencia agencia = manager.find(Agencia.class, idAgencia);
			Pais pais = manager.find(Pais.class,agencia.getPais().getIdPais());
			PaisDTO paisDto = new PaisDTO(pais.getIdPais(),pais.getNombre());
			AgenciaDTO ag = new AgenciaDTO(agencia.getNombre(),agencia.isEstado(),agencia.getCalle(),agencia.getNro(),agencia.getPiso(), agencia.getDepto(),agencia.getLocalidad(),paisDto,agencia.getFechaCreacion(),agencia.getEmail());
			ag.setIdAgencia(idAgencia);
			
			ProvinciaDTO prov = new ProvinciaDTO();
			prov.setIdProvincia(agencia.getProvincia().getIdProvincia());
			prov.setNombre(agencia.getProvincia().getNombre());
			ag.setProvincia(prov);
			
			List<Paquete> paquetes = agencia.getPaquetes();
			List<PaqueteDTO> listPaquetesDto = new ArrayList<PaqueteDTO>();
			for(Paquete p:paquetes){
				PaqueteDTO pdto = new PaqueteDTO(p.getNombre(),p.getFechaDesde(),p.getFechaHasta(),p.getDescripcion(),p.getPrecio(),p.getPoliticasCancelacion(),p.getCupo(),p.getCantPersonas(),p.isEstado(),p.isNuevaOferta());
				pdto.setIdPaquete(p.getIdPaquete());
				listPaquetesDto.add(pdto);
			}
			
			return ag;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al obtener agencia "+ idAgencia);
		}
		return null;
	}
	
	private int postAgencias(AgenciaDTO dto)
	{
		try{
			System.out.println("Enviar Agencia");
			URL url = new URL(Properties.URL_POST_AGENCIA);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");
			
			//Armo el json String
			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
		   			.add("detalle",dto.getNombre())
		   			.add("tipo","Agencia");
			   				
			   	JsonObject agenciaJson = jsonBuilder.build();
		        StringWriter stringWriter = new StringWriter();
		        
		        JsonWriter writer = Json.createWriter(stringWriter);
		        writer.writeObject(agenciaJson);
		        writer.close();
		        
		        String json = agenciaJson.toString();
			
			IOUtils.write(json, urlConnection.getOutputStream());
			
			AdministradorLogs log = new AdministradorLogs();
			if(urlConnection.getResponseCode() != 200) {
				String observacion = "Response code: "+urlConnection.getResponseCode();
				log.enviarLog("Oferta Paquetes", "Back Office", "EnviarSolicitud",observacion);
				
				throw new RuntimeException("Error de conexion: " + urlConnection.getResponseCode());
			}
			else
				log.enviarLog("Oferta Paquetes", "Back Office", "EnviarSolicitud","Envio Solicitud Agencia Exitoso");
			
			String response = IOUtils.toString(urlConnection.getInputStream());

			JSONObject jsonObj = new JSONObject(response);
			int id = jsonObj.getInt("id");
			return id;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al querer enviar solicitud de agencia");
			
			AdministradorLogs log = new AdministradorLogs();
			log.enviarLog("Oferta Paquetes", "Back Office", "EnviarSolicitud", e.getMessage());
		}
		return 0;
	}
}