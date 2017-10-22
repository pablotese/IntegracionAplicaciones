package com.ofertaPaquetes.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class SolicitudDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	int idSolicitud;
	Date fechaCreacion;
	String estado;

	public SolicitudDTO(Date fechaCreacion, String estado) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
