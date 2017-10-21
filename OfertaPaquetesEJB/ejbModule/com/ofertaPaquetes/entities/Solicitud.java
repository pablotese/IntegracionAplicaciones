package com.ofertaPaquetes.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ofertaPaquetes.util.EnumEstadoSolicitud;

@Entity
@Table(name="Solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int idSolicitud;

	Date fechaCreacion;

	String estado;
	
	@ManyToOne
	@JoinColumn(name="idAgencia")
	Agencia agencia;

	public Solicitud(Date fechaCreacion, String estado, Agencia agencia) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.agencia = agencia;
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

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	
}
