package com.ofertaPaquetes.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class PaqueteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idPaquete;
	private String nombre;
	private Date fechaDesde;
	private Date fechaHasta;
	private String descripcion;
	private Double precio;
	private String politicasCancelacion;
	private int cupo;
	private int cantPersonas;
	private boolean estado;
	private boolean nuevaOferta;
	private String imagen;
	private AgenciaDTO agencia;
	private DestinoDTO destino;
	private float latitud;
    private float longitud;
    private List<MedioDePagoDTO> mediosDePago;
	private List<PaqueteServicioDTO> servicios;
	 
	
	
	public PaqueteDTO(String nombre, Date fechaDesde, Date fechaHasta, String descripcion, Double precio,
			String politicasCancelacion, int cupo, int cantPersonas, boolean estado, boolean nuevaOferta) {
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
	
	
	
	public PaqueteDTO(int idPaquete, String nombre, Date fechaDesde, Date fechaHasta, String descripcion, Double precio,
			String politicasCancelacion, int cupo, int cantPersonas, boolean estado, boolean nuevaOferta, String imagen,
			AgenciaDTO agencia, DestinoDTO destino, float latitud, float longitud, List<MedioDePagoDTO> mediosDePago,
			List<PaqueteServicioDTO> servicios) {
		super();
		this.idPaquete = idPaquete;
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
		this.imagen = imagen;
		this.agencia = agencia;
		this.destino = destino;
		this.latitud = latitud;
		this.longitud = longitud;
		this.mediosDePago = mediosDePago;
		this.servicios = servicios;
	}

	public PaqueteDTO(String nombre, Date fechaDesde, Date fechaHasta, String descripcion, Double precio,
			String politicasCancelacion, int cupo, int cantPersonas, boolean estado, boolean nuevaOferta, String imagen,
			AgenciaDTO agencia, DestinoDTO destino, List<PaqueteServicioDTO> servicios) {
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
		this.imagen = imagen;
		this.agencia = agencia;
		this.destino = destino;
		this.servicios = servicios;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public AgenciaDTO getAgencia() {
		return agencia;
	}
	public void setAgencia(AgenciaDTO agencia) {
		this.agencia = agencia;
	}
	public DestinoDTO getDestino() {
		return destino;
	}
	public void setDestino(DestinoDTO destino) {
		this.destino = destino;
	}
	public List<PaqueteServicioDTO> getServicios() {
		return servicios;
	}
	public void setServicios(List<PaqueteServicioDTO> servicios) {
		this.servicios = servicios;
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
	public List<MedioDePagoDTO> getMediosDePago() {
		return mediosDePago;
	}
	public void setMediosDePago(List<MedioDePagoDTO> mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
	@Override
	public String toString() {
		return "PaqueteDTO [idPaquete=" + idPaquete + ", nombre=" + nombre + ", fechaDesde=" + fechaDesde
				+ ", fechaHasta=" + fechaHasta + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", politicasCancelacion=" + politicasCancelacion + ", cupo=" + cupo + ", cantPersonas=" + cantPersonas
				+ ", estado=" + estado + ", nuevaOferta=" + nuevaOferta + ", agencia=" + agencia.getIdAgencia() + "]";
	}
	
	
}
