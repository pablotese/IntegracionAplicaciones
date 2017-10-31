package com.ofertaPaquetes.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Agencias")
public class Agencia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAgencia;
	
	private int idAgenciaBO;

	private String nombre;

	private boolean estado;
	private String calle;
	private int nro;
	private String piso;
	private String depto;
	private String localidad;
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name="idPais")
	private Pais pais;
	
	

	@OneToMany(mappedBy="agencia")
	private List<Paquete> paquetes;

	
	
	public Agencia() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Agencia(String nombre, boolean estado, String calle, int nro, String piso, String depto, String localidad,
			Pais pais, Date fechaAgencia) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.calle = calle;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.pais = pais;
		this.fechaCreacion=fechaAgencia;
	}
	
	
	public Agencia(int idAgencia, int idAgenciaBO, String nombre, boolean estado, String calle, int nro, String piso, String depto,
			String localidad, Pais pais, Date fechaCreacion, List<Paquete> paquetes) {
		super();
		this.idAgencia = idAgencia;
		this.idAgenciaBO = idAgenciaBO;
		this.nombre = nombre;
		this.estado = estado;
		this.calle = calle;
		this.nro = nro;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.pais = pais;
		this.fechaCreacion= fechaCreacion;
		this.paquetes = paquetes;
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

	public List<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<Paquete> paquetes) {
		this.paquetes = paquetes;
	}
	
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	
	public int getIdAgenciaBO() {
		return idAgenciaBO;
	}


	public void setIdAgenciaBO(int idAgenciaBO) {
		this.idAgenciaBO = idAgenciaBO;
	}


	@Override
	public String toString() {
		return "Agencia [idAgencia=" + idAgencia + ", nombre=" + nombre + ", estado=" + estado + ", paquetes="
				+ paquetes + "]";
	}
	
	
}
