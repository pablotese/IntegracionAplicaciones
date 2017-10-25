package com.ofertaPaquetes.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSolicitud;
	private Date fechaCreacion;
	private String estado;


	public Solicitud(Date fechaCreacion, String estado) {
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

	public Solicitud() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
