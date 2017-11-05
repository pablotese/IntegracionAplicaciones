package com.ofertaPaquetes.sessionBeans;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Pais;
import com.ofertaPaquetes.entities.Paquete;
import com.ofertaPaquetes.entities.Provincia;

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

			manager.persist(agencia);
			
			AdministradorLogs log = new AdministradorLogs();
			Date d = new Date();
			Timestamp t=new Timestamp(d.getTime());
			long time = t.getTime();
			log.enviarLog("Oferta Paquetes", "Back Office",time, "Crear Agencia", "");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al guardar Agencia");
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
}
