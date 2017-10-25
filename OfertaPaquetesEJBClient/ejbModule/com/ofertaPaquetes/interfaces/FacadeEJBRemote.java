package com.ofertaPaquetes.interfaces;

import java.util.List;

import com.ofertaPaquetes.dtos.AgenciaDTO;

public interface FacadeEJBRemote {
	public void nuevaAgencia(AgenciaDTO agenciaDto);
	public void modificarAgencia(AgenciaDTO agenciaDto);
	public void eliminarAgencia(int idAgencia);
	public void modificarEstadoSolicitud(int idSolicitud, String estado);		
	public List<AgenciaDTO> listarAgenciasPorEstado(String estado);
}
