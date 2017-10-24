package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class PaisDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idPais;
	private String nombre;
	
	
	public PaisDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public PaisDTO(int idPais, String nombre) {
		super();
		this.idPais = idPais;
		this.nombre = nombre;
	}



	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
