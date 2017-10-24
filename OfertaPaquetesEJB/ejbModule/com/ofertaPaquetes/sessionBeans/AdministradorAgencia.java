package com.ofertaPaquetes.sessionBeans;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Pais;

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
		try{
			manager.persist(agencia);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al guardar Agencia");
		}
	}

}
