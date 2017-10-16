package com.ofertaPaquetes;

import javax.ws.rs.*;
@Path("/service")
public class JAXRSExample {
	
	@GET @Path("/hola") @Produces({ "text/plain" })
	public String hola() {
		return "Hola!";
	}
	
	@POST @Path("/saludo") @Produces({ "text/plain" })
	public String saludo(String nombre) {
		return "Hola " + nombre;
	}
}
