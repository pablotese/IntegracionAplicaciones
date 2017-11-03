package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class MedioDePagoDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idMedioDePago;
	private String nombre;
	
	
	public MedioDePagoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MedioDePagoDTO(int idMedioDePago, String nombre) {
		super();
		this.idMedioDePago = idMedioDePago;
		this.nombre = nombre;
	}
	public int getIdMedioDePago() {
		return idMedioDePago;
	}
	public void setIdMedioDePago(int idMedioDePago) {
		this.idMedioDePago = idMedioDePago;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
