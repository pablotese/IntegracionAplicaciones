package com.ofertaPaquetes.jsons;

public class EstadoSolicitudJSONRequest {
	
	
	String tipo;
	String detalle;
	
	public EstadoSolicitudJSONRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstadoSolicitudJSONRequest(String tipo, String detalle) {
		super();
		this.tipo = tipo;
		this.detalle = detalle;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
	

}
