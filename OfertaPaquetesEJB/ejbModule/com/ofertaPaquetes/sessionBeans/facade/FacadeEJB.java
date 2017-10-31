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
	
	
	public List<AgenciaDTO> listarAgencias(){
		return ag.listarAgencias();
	}

	@Override
	public AgenciaDTO obtenerAgencia(int idAgencia) {
		// TODO Auto-generated method stub
		return ag.obtenerAgencia(idAgencia);
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
	
	@Override
	public PaqueteDTO obtenerPaquete(int idPaquete){
		return ap.obtenerPaquete(idPaquete);
	}

	@Override
	public List<AgenciaDTO> listarAgenciasPorEstado(boolean estado) {
		// TODO Auto-generated method stub
		return ag.listarAgenciasPorEstado(estado);
	}
	
}
