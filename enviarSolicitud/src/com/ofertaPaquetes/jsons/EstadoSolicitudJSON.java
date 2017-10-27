package com.ofertaPaquetes.jsons;

public class EstadoSolicitudJSON {
	
	int idSolicitud;
	String estado;
	
	
	public EstadoSolicitudJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstadoSolicitudJSON(int idSolicitud, String estado) {
		super();
		this.idSolicitud = idSolicitud;
		this.estado = estado;
	}
	public int getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
