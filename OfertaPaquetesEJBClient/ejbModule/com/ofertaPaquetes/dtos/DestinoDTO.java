package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class DestinoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idDestino;
	private float latitud;
	private float longitud;
	private String nombre;
	public DestinoDTO(int idDestino, String nombre) {
		super();
		this.idDestino = idDestino;
		this.nombre = nombre;
	}
	
	
	public DestinoDTO(int idDestino) {
		super();
		this.idDestino = idDestino;
	}


	public DestinoDTO() {
		super();
		// TODO Auto-generated constructor stub
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


	public float getLatitud() {
		return latitud;
	}


	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}


	public float getLongitud() {
		return longitud;
	}


	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}


	
}
