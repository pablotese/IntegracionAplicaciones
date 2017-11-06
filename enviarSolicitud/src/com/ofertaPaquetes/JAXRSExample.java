package com.ofertaPaquetes;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;

import org.apache.commons.io.IOUtils;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.jsons.AgenciaJSON;
import com.ofertaPaquetes.jsons.EstadoSolicitudJSON;
import com.ofertaPaquetes.jsons.EstadoSolicitudJSONRequest;
import com.ofertaPaquetes.jsons.LogJSON;
import com.ofertaPaquetes.jsons.ServicioJSON;
import com.ofertaPaquetes.jsons.TipoServicioJSON;

@Path("/service")
public class JAXRSExample {

	@GET
	@Path("/GetServicios")
	@Produces({"application/json"})
	public List<ServicioJSON> getServicios() {
		try{
			
			List<ServicioJSON> test=new ArrayList<ServicioJSON>();
			test.add(new ServicioJSON(1,new TipoServicioJSON(1,"Habitacion"),"wifi"));
			test.add(new ServicioJSON(2,new TipoServicioJSON(1,"Habitacion"),"despertador"));
			test.add(new ServicioJSON(3,new TipoServicioJSON(2,"Transporte"),"wifi"));
			return test;
		}
		catch(Exception e){
			System.out.println("Fallo");
			e.printStackTrace();
			
		}
		return null;
	}
	
	@POST 
	@Path("/EnviarSolicitud") 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public EstadoSolicitudJSON enviarSolicitud(EstadoSolicitudJSONRequest request) {
		try {
			EstadoSolicitudJSON json = new EstadoSolicitudJSON(123,request.getTipo(),request.getDetalle(),"Pendiente");
			return json;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@POST 
	@Path("/RegistrarLog") 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public String registrarLog(LogJSON jlog) {
		try {
			
			return jlog.toString();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Error";
		
	}
}
