package com.ofertaPaquetes.jsons;

public class ServicioJSON {
	private int id;
	private TipoServicioJSON tipo;
	private String nombre;
	public ServicioJSON(int id, TipoServicioJSON tipo, String nombre) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
	}
	public ServicioJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoServicioJSON getTipo() {
		return tipo;
	}
	public void setTipo(TipoServicioJSON tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
