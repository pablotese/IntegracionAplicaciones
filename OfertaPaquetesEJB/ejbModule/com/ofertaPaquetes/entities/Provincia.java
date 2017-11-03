package com.ofertaPaquetes.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Provincias")
public class Provincia {
	private int idProvincia;
	private String nombre;
	
	public Provincia(int idProvincia, String nombre) {
		super();
		this.idProvincia = idProvincia;
		this.nombre = nombre;
	}
	
	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
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
