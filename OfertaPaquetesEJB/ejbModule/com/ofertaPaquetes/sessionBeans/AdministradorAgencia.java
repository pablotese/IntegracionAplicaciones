package com.ofertaPaquetes.sessionBeans;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Pais;
import com.ofertaPaquetes.entities.Paquete;

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
				agenciaDto.getDepto(), agenciaDto.getLocalidad(),pais,agenciaDto.getFechaCreacion());
		
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
							
				AgenciaDTO dto= new AgenciaDTO(ag.getNombre(),ag.isEstado(),ag.getCalle(),ag.getNro(),ag.getPiso(),ag.getDepto(),ag.getLocalidad(),pais,ag.getFechaCreacion());
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
	
	public AgenciaDTO obtenerAgencia(int idAgencia){
		try{
			Agencia agencia = manager.find(Agencia.class, idAgencia);
			Pais pais = manager.find(Pais.class,agencia.getPais().getIdPais());
			PaisDTO paisDto = new PaisDTO(pais.getIdPais(),pais.getNombre());
			AgenciaDTO ag = new AgenciaDTO(agencia.getNombre(),agencia.isEstado(),agencia.getCalle(),agencia.getNro(),agencia.getPiso(), agencia.getDepto(),agencia.getLocalidad(),paisDto,agencia.getFechaCreacion());
			
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
