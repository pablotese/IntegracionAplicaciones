package com.ofertaPaquetes.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="MedioDePago")
public class MedioDePago {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int idMedioDePago;
	
	private String nombre;
	
	@ManyToMany(mappedBy="servicios")
	private List<Paquete> paquetes;

	public MedioDePago() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedioDePago(int idMedioDePago, String nombre) {
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

	public List<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<Paquete> paquetes) {
		this.paquetes = paquetes;
	}
	
	
	
}
