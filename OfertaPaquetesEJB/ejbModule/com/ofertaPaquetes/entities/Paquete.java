package com.ofertaPaquetes.entities;

import javax.persistence.*;

@Entity
public class Paquete {
	  @Id @GeneratedValue(strategy=GenerationType.AUTO)   
	    Long id;
	    String descripcion;
	    public Paquete(String descripcion) {
	        this.descripcion = descripcion;
	    }
}
