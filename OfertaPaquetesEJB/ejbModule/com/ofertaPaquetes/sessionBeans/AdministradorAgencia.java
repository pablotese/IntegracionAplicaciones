package com.ofertaPaquetes.sessionBeans;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.SolicitudDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Pais;
import com.ofertaPaquetes.entities.Solicitud;

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
		
		/****************************************************/
		/*Este codigo debe removerse, por ahora sirve para crear pais al inicializar de cero y borrar la bd*/
		Pais pais = new Pais(agenciaDto.getPais().getNombre());
		try{
			
			manager.persist(pais);
			manager.flush();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al guardar pais");
		} 
		/*
		 * Pais pais =manager.find(Pais.class, agenciaDto.getPais().getIdPais());
		 * */
		/****************************************************/
		
		Agencia agencia = new Agencia(agenciaDto.getNombre(),false,agenciaDto.getCalle(), agenciaDto.getNro(),agenciaDto.getPiso(),
				agenciaDto.getDepto(), agenciaDto.getLocalidad(),pais);
		
		/*Al crearse una Agencia siempre se creará una solicitud pendiente.
		 * Quizás deba realizarse en este método la creación sin esperar el estado desde el admin 
		 */
		Solicitud solicitud = new Solicitud(agenciaDto.getSolicitud().getFechaCreacion(), agenciaDto.getSolicitud().getEstado());
		agencia.setSolicitud(solicitud);  
		
		try{
			manager.persist(agencia);
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
			agencia.setDepto(ag.getDepto());
			agencia.setEstado(ag.isEstado());
			agencia.setLocalidad(ag.getLocalidad());
			agencia.setNombre(ag.getNombre());
			agencia.setNro(ag.getNro());
			agencia.setPiso(ag.getPiso());
			
			Pais pais = manager.find(Pais.class, ag.getPais().getIdPais());
			agencia.setPais(pais);
			
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
	
	public void modificarEstadoSolicitud(int idSolicitud, String estado){
		try{
			Solicitud solicitud = manager.find(Solicitud.class, idSolicitud);
			solicitud.setEstado(estado);
			manager.merge(solicitud);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al actualizar el estado de la solicitud");
		}
	}

	public List<AgenciaDTO> listarAgenciasPorEstado(String estado){

		try{
			List<AgenciaDTO> lista = new ArrayList<AgenciaDTO>();
		
			List<Agencia> agencias = (List<Agencia>) manager.createQuery(" FROM Agencia  a "
				+ "WHERE a.solicitud.estado like :estado").setParameter("estado",estado)
				.getResultList();
		
			for(Agencia ag:agencias){
				
				PaisDTO pais = new PaisDTO(ag.getPais().getNombre());
				pais.setIdPais(ag.getPais().getIdPais());
				
				SolicitudDTO solicitud = new SolicitudDTO(ag.getSolicitud().getFechaCreacion(), ag.getSolicitud().getEstado());
				solicitud.setIdSolicitud(ag.getSolicitud().getIdSolicitud());
							
				AgenciaDTO dto= new AgenciaDTO(ag.getNombre(),ag.isEstado(),ag.getCalle(),ag.getNro(),ag.getPiso(),ag.getDepto(),ag.getLocalidad(),pais);
				
				dto.setSolicitud(solicitud);
				
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
