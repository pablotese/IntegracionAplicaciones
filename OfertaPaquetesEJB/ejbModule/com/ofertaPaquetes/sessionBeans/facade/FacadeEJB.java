package com.ofertaPaquetes.sessionBeans.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.interfaces.FacadeEJBLocal;
import com.ofertaPaquetes.interfaces.FacadeEJBRemote;
import com.ofertaPaquetes.sessionBeans.AdministradorAgencia;

/**
 * Session Bean implementation class FacadeEJB
 */
@Stateless
@Remote
public class FacadeEJB implements FacadeEJBLocal, FacadeEJBRemote {

	@EJB
	AdministradorAgencia ag;
    /**
     * Default constructor. 
     */
    public FacadeEJB() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void nuevaAgencia(AgenciaDTO agenciaDto) {
		ag.nuevaAgencia(agenciaDto);
	}
	
	@Override
	public void modificarAgencia(AgenciaDTO agenciaDto){
		ag.modificarAgencia(agenciaDto);
	}
	
	@Override
	public void eliminarAgencia(int idAgencia){
		ag.eliminarAgencia(idAgencia);
	}
	
	@Override
	public void modificarEstadoSolicitud(int idSolicitud, String estado){
		ag.modificarEstadoSolicitud(idSolicitud, estado);
	}
		
	@Override
	public List<AgenciaDTO> listarAgenciasPorEstado(String estado){
		return ag.listarAgenciasPorEstado(estado);
	}

}
