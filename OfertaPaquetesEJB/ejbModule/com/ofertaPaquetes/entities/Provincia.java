package com.ofertaPaquetes.entities;

import javax.persistence.Entity;
<<<<<<< e53ae2d6982bd551d175458ab8393d7901746d5c
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
=======
>>>>>>> Cambio a entities
import javax.persistence.Table;

@Entity
@Table(name="Provincias")
public class Provincia {
<<<<<<< e53ae2d6982bd551d175458ab8393d7901746d5c
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
=======
>>>>>>> Cambio a entities
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
