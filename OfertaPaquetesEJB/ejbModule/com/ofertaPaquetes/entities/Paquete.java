package com.ofertaPaquetes.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Paquetes")
public class Paquete {
	    @Id @GeneratedValue(strategy=GenerationType.AUTO)   
	    private int idPaquete;
	    
	    private String nombre;
	    private Date fechaDesde;
	    private Date fechaHasta;
	    private String descripcion;
	    private Double precio;
	    private String politicasCancelacion;
	    private int cupo;
	    
	    @ManyToMany
	    @JoinTable(
	            name = "PaquetesServicios", 
	            joinColumns = { @JoinColumn(name = "idPaquete") }, 
	            inverseJoinColumns = { @JoinColumn(name = "idTipoServicio") }
	        )
	    private List<TipoServicio> servicios;
	    
	    @ManyToOne
	    @JoinColumn(name="idDestino")
	    private Destino destino;
	    
	    private int cantPersonas;
	    private boolean estado;
	    private boolean nuevaOferta;
	    
	    @ManyToOne
	    @JoinColumn(name="idAgencia")
	    private Agencia agencia;
	    
	    @OneToMany(mappedBy = "paquete", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	    private List<Imagen> imagenes;
	    
	    	    
		public Paquete(String nombre, Date fechaDesde, Date fechaHasta, String descripcion, Double precio,
				String politicasCancelacion, int cupo, int cantPersonas,
				boolean estado, boolean nuevaOferta) {
			super();
			this.nombre = nombre;
			this.fechaDesde = fechaDesde;
			this.fechaHasta = fechaHasta;
			this.descripcion = descripcion;
			this.precio = precio;
			this.politicasCancelacion = politicasCancelacion;
			this.cupo = cupo;
			this.cantPersonas = cantPersonas;
			this.estado = estado;
			this.nuevaOferta = nuevaOferta;
		}
		public int getIdPaquete() {
			return idPaquete;
		}
		public void setIdPaquete(int idPaquete) {
			this.idPaquete = idPaquete;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Date getFechaDesde() {
			return fechaDesde;
		}
		public void setFechaDesde(Date fechaDesde) {
			this.fechaDesde = fechaDesde;
		}
		public Date getFechaHasta() {
			return fechaHasta;
		}
		public void setFechaHasta(Date fechaHasta) {
			this.fechaHasta = fechaHasta;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Double getPrecio() {
			return precio;
		}
		public void setPrecio(Double precio) {
			this.precio = precio;
		}
		public String getPoliticasCancelacion() {
			return politicasCancelacion;
		}
		public void setPoliticasCancelacion(String politicasCancelacion) {
			this.politicasCancelacion = politicasCancelacion;
		}
		public int getCupo() {
			return cupo;
		}
		public void setCupo(int cupo) {
			this.cupo = cupo;
		}
		public List<TipoServicio> getServicios() {
			return servicios;
		}
		public void setServicios(List<TipoServicio> servicios) {
			this.servicios = servicios;
		}
		public Destino getDestino() {
			return destino;
		}
		public void setDestino(Destino destino) {
			this.destino = destino;
		}
		public int getCantPersonas() {
			return cantPersonas;
		}
		public void setCantPersonas(int cantPersonas) {
			this.cantPersonas = cantPersonas;
		}
		public boolean isEstado() {
			return estado;
		}
		public void setEstado(boolean estado) {
			this.estado = estado;
		}
		public boolean isNuevaOferta() {
			return nuevaOferta;
		}
		public void setNuevaOferta(boolean nuevaOferta) {
			this.nuevaOferta = nuevaOferta;
		}
		public List<Imagen> getImagenes() {
			return imagenes;
		}
		public void setImagenes(List<Imagen> imagenes) {
			this.imagenes = imagenes;
		}
		
			
		public Agencia getAgencia() {
			return agencia;
		}
		public void setAgencia(Agencia agencia) {
			this.agencia = agencia;
		}
		@Override
		public String toString() {
			return "Paquete [idPaquete=" + idPaquete + ", nombre=" + nombre + ", descripcion=" + descripcion
					+ ", precio=" + precio + ", politicasCancelacion=" + politicasCancelacion + ", cupo=" + cupo
					+ ", cantPersonas=" + cantPersonas + ", estado=" + estado + ", nuevaOferta=" + nuevaOferta + "]";
		}
	    
	    
}
