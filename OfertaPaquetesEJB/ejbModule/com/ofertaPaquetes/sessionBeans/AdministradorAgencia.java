package com.ofertaPaquetes.sessionBeans;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.entities.Agencia;

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
		
		Agencia agencia = new Agencia(agenciaDto.getNombre(), false);
		try{
			manager.persist(agencia);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Conectandose a "+ e.getMessage());
		}
	}

}
