package com.ofertaPaquetes.interfaces;

import java.util.List;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;

public interface FacadeEJBRemote {
	public void nuevaAgencia(AgenciaDTO agenciaDto);
	public void modificarAgencia(AgenciaDTO agenciaDto);
	public void eliminarAgencia(int idAgencia);
	public void modificarEstadoSolicitud(int idSolicitud, String estado);		
	public List<AgenciaDTO> listarAgenciasPorEstado(String estado);
	
	public void nuevoPaquete(PaqueteDTO paqueteDto);
	public void modificarPaquete(PaqueteDTO paqueteDto);
	public void eliminarPaquete(int idPaquete);		
	public List<PaqueteDTO> listarPaquetes();
}
