package com.ofertaPaquetes.dtos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.OneToMany;

public class AgenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	int idAgencia;
	String nombre;
	boolean estado;
	SolicitudDTO solicitud;

	List<PaqueteDTO> paquetes;

	public AgenciaDTO(String nombre, boolean estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<PaqueteDTO> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteDTO> paquetes) {
		this.paquetes = paquetes;
	}

	public SolicitudDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}

	
	
}
