package com.ofertaPaquetes.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PaquetesServicios")
public class PaqueteServicio {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int idPaqueteServicio;

	private int idPaquete;

	private int idServicio;
	private String nombreServicio;
	

	public PaqueteServicio(int idServicio, String nombreServicio) {
		super();
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
	}

	

	public PaqueteServicio(int idPaquete, int idServicio, String nombreServicio) {
		super();
		this.idPaquete = idPaquete;
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
	}



	public int getIdPaquete() {
		return idPaquete;
	}



	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}



	public PaqueteServicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public int getIdPaqueteServicio() {
		return idPaqueteServicio;
	}

	public void setIdPaqueteServicio(int idPaqueteServicio) {
		this.idPaqueteServicio = idPaqueteServicio;
	}
	
	
}
