package com.ofertaPaquetes.sessionBeans.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.interfaces.FacadeEJBLocal;
import com.ofertaPaquetes.interfaces.FacadeEJBRemote;
import com.ofertaPaquetes.sessionBeans.AdministradorAgencia;
import com.ofertaPaquetes.sessionBeans.AdministradorPaquete;

/**
 * Session Bean implementation class FacadeEJB
 */
@Stateless
@Remote
public class FacadeEJB implements FacadeEJBLocal, FacadeEJBRemote {

	@EJB
	AdministradorAgencia ag;
	@EJB
	AdministradorPaquete ap;
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

	@Override
	public void nuevoPaquete(PaqueteDTO paqueteDto) {
		ap.nuevoPaquete(paqueteDto);
	}

	@Override
	public void modificarPaquete(PaqueteDTO paqueteDto) {
		ap.modificarPaquete(paqueteDto);
	}

	@Override
	public void eliminarPaquete(int idPaquete) {
		ap.eliminarPaquete(idPaquete);	
	}

	@Override
	public List<PaqueteDTO> listarPaquetes() {
		return ap.listarPaquetes();
	}

}
