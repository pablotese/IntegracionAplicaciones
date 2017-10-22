package com.ofertaPaquetes.entities;

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
	int idAgencia;

	String nombre;

	boolean estado;
	
	@OneToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="idSolicitud")
	Solicitud solicitud;

	@OneToMany(mappedBy="agencia")
	List<Paquete> paquetes;

	public Agencia(String nombre, boolean estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
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
	
	

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	@Override
	public String toString() {
		return "Agencia [idAgencia=" + idAgencia + ", nombre=" + nombre + ", estado=" + estado + ", paquetes="
				+ paquetes + "]";
	}
	
	
}
