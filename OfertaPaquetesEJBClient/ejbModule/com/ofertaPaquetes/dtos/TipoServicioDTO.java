package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class TipoServicioDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int idTipoServicio;
	private String nombre;
	private String descripcion;
	public TipoServicioDTO(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public int getIdTipoServicio() {
		return idTipoServicio;
	}
	public void setIdTipoServicio(int idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
}
