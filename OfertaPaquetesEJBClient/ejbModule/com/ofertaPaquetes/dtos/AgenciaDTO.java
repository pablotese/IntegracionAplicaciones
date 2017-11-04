package com.ofertaPaquetes.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AgenciaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idAgencia;
	private int idAgenciaBO;
	private String nombre;
	private boolean estado;
	private String calle;
	private Date fechaCreacion;
	private int nro;
	private String piso;
	private String depto;
	private String localidad;
	private PaisDTO pais;
	private ProvinciaDTO provincia;
	private String email;
	private List<PaqueteDTO> paquetes;

	public AgenciaDTO(String nombre, boolean estado, String calle, int nro,
			String piso, String depto, String localidad, PaisDTO pais, Date fechaCreacion) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.calle = calle;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.pais = pais;
		this.fechaCreacion = fechaCreacion;
	}
	
	public  AgenciaDTO(String nombre, boolean estado, String calle, int nro,
			String piso, String depto, String localidad, PaisDTO pais, Date fechaCreacion, String email){
		this.nombre = nombre;
		this.estado = estado;
		this.calle = calle;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.pais = pais;
		this.fechaCreacion = fechaCreacion;
		this.email=email;
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

	
	public int getIdAgenciaBO() {
		return idAgenciaBO;
	}



	public void setIdAgenciaBO(int idAgenciaBO) {
		this.idAgenciaBO = idAgenciaBO;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	



	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AgenciaDTO [idAgencia=" + idAgencia + ", nombre=" + nombre + ",  calle=" + calle + ", nro=" + nro + ", piso=" + piso + ", depto=" + depto
				+ ", localidad=" + localidad + ", pais=" + pais.getNombre() + "]";
	}

	
	
	
}
