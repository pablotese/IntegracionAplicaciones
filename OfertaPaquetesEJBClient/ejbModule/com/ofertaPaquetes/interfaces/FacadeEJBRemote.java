package com.ofertaPaquetes.interfaces;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.MedioDePagoDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;

public interface FacadeEJBRemote {
	public void nuevaAgencia(AgenciaDTO agenciaDto);
	public void modificarAgencia(AgenciaDTO agenciaDto);
	public void eliminarAgencia(int idAgencia);
	public List<AgenciaDTO> listarAgenciasPorEstado(boolean estado);
	public List<AgenciaDTO> listarAgencias();
	public AgenciaDTO obtenerAgencia(int idAgencia);
	
	public void nuevoPaquete(PaqueteDTO paqueteDto);
	public void modificarPaquete(PaqueteDTO paqueteDto);
	public void eliminarPaquete(int idPaquete);		
	public List<PaqueteDTO> listarPaquetes();
	public PaqueteDTO obtenerPaquete(int idPaquete);
	
	public List<ProvinciaDTO> getListadoProvincias();
	public List<MedioDePagoDTO> getListadoMediosDePago();
	public MedioDePagoDTO obtenerMedioPago(int idMedioPago);
	public void cargarDatosIniciales();
	
	public List<DestinoDTO> listarDestinos();
	public DestinoDTO obtenerDestino(int idDestino);
	
	public void enviarLog(String plataformaEnvia, String plataformaRecibe, String servicio,
			String observacion);
}
