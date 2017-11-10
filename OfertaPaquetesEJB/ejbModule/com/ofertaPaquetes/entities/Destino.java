package com.ofertaPaquetes.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Destinos")
public class Destino {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int idDestino;

	private String nombre;
	
	@OneToMany(mappedBy="destino")
	private List<Paquete> paquetes;
	
	public Destino(int idDestino, String nombre) {
		super();
		this.idDestino = idDestino;
		this.nombre = nombre;
	}
	

	public int getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public Destino() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
