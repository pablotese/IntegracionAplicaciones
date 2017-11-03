package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class ProvinciaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProvincia;
	private String nombre;
	public ProvinciaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProvinciaDTO(int idProvincia, String nombre) {
		super();
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
