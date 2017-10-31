package com.ofertaPaquetes.jsons;

public class EstadoSolicitudJSON {
	
	int id;
	String nombre;
	String estado;
	
	
	public EstadoSolicitudJSON(int id, String nombre, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
	}
	public EstadoSolicitudJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
