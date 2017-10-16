package com.ofertaPaquetes.dtos;

public class PaqueteDTO {

	private Long id;
	private String descripcion;
	
	
	public PaqueteDTO(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	
}
