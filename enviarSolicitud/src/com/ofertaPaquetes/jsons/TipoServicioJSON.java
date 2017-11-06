package com.ofertaPaquetes.jsons;

public class TipoServicioJSON {
	private int id;
	private String nombre;
	public TipoServicioJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TipoServicioJSON(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
