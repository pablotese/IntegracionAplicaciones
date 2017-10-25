package com.ofertaPaquetes.dtos;

import java.io.Serializable;

public class ImagenDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idImagen;
	private byte[] imagen;
	
	public ImagenDTO(int idImagen, byte[] imagen) {
		super();
		this.idImagen = idImagen;
		this.imagen = imagen;
	}
	public int getIdImagen() {
		return idImagen;
	}
	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
}
