package com.ofertaPaquetes.dtos;

import java.io.Serializable;
import java.util.List;

public class AgenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idAgencia;
	private String nombre;
	private boolean estado;
	private SolicitudDTO solicitud;
	private String calle;
	private int nro;
	private String piso;
	private String depto;
	private String localidad;
	private PaisDTO pais;
	private List<PaqueteDTO> paquetes;

	public AgenciaDTO(String nombre, boolean estado, String calle, int nro,
			String piso, String depto, String localidad, PaisDTO pais) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.calle = calle;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.pais = pais;
	}
	
	

	public AgenciaDTO(int idAgencia) {
		super();
		this.idAgencia = idAgencia;
	}



	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<PaqueteDTO> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteDTO> paquetes) {
		this.paquetes = paquetes;
	}

	public SolicitudDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}


	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "AgenciaDTO [idAgencia=" + idAgencia + ", nombre=" + nombre + ", solicitud creada="
				+ solicitud.getFechaCreacion() + ", calle=" + calle + ", nro=" + nro + ", piso=" + piso + ", depto=" + depto
				+ ", localidad=" + localidad + ", pais=" + pais.getNombre() + "]";
	}

	
	
	
}
