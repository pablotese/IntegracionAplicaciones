package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class PaqueteServicioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idPaquete;
	private int idServicio;
	private String nombreServicio;
	public PaqueteServicioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PaqueteServicioDTO(int idServicio, String nombreServicio) {
		super();
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
	}



	public PaqueteServicioDTO(int idPaquete, int idServicio, String nombreServicio) {
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
	
	
	
}
