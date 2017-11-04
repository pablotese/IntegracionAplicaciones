package com.ofertaPaquetes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Paises")
public class Pais {

	@Id
	private int idPais;
	private String nombre;
	
	public Pais(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	
	public Pais(int idPais, String nombre) {
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
	public Pais() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
