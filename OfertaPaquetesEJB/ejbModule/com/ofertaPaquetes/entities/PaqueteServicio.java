package com.ofertaPaquetes.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
<<<<<<< e53ae2d6982bd551d175458ab8393d7901746d5c
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
>>>>>>> Cambio a entities
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PaquetesServicios")
public class PaqueteServicio {

<<<<<<< e53ae2d6982bd551d175458ab8393d7901746d5c
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int idPaqueteServicio;
	
=======
>>>>>>> Cambio a entities
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idPaquete")
	private Paquete paquete;

	private int idServicio;
	private String nombreServicio;
	

	public PaqueteServicio(int idServicio, String nombreServicio) {
		super();
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
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
<<<<<<< e53ae2d6982bd551d175458ab8393d7901746d5c

	public int getIdPaqueteServicio() {
		return idPaqueteServicio;
	}

	public void setIdPaqueteServicio(int idPaqueteServicio) {
		this.idPaqueteServicio = idPaqueteServicio;
	}
=======
>>>>>>> Cambio a entities
	
	
}
