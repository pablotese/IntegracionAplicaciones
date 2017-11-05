package com.ofertaPaquetes.jsons;

import java.sql.Timestamp;
import java.util.Date;

public class LogJSON {
	private String plataformaEnvia;
	private String plataformaRecibe;
	private Long fecha;
	private String servicio;
	private String observacion;
	public LogJSON() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPlataformaEnvia() {
		return plataformaEnvia;
	}
	public void setPlataformaEnvia(String plataformaEnvia) {
		this.plataformaEnvia = plataformaEnvia;
	}
	public String getPlataformaRecibe() {
		return plataformaRecibe;
	}
	public void setPlataformaRecibe(String plataformaRecibe) {
		this.plataformaRecibe = plataformaRecibe;
	}
	
	public Long getFecha() {
		return fecha;
	}
	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public LogJSON(String plataformaEnvia, String plataformaRecibe, Long fecha, String servicio, String observacion) {
		super();
		this.plataformaEnvia = plataformaEnvia;
		this.plataformaRecibe = plataformaRecibe;
		this.fecha = fecha;
		this.servicio = servicio;
		this.observacion = observacion;
	}
	@Override
	public String toString() {
		return "LogJSON [plataformaEnvia=" + plataformaEnvia + ", plataformaRecibe=" + plataformaRecibe + ", fecha="
				+ fecha + ", servicio=" + servicio + ", observacion=" + observacion + "]";
	}
	
	
}
