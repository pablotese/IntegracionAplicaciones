package com.ofertaPaquetes;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.jsons.AgenciaJSON;
import com.ofertaPaquetes.jsons.EstadoSolicitudJSON;

@Path("/service")
public class JAXRSExample {

	@GET
	@Path("/enviarSolicitud")
	@Produces({"application/json"})
	public List<AgenciaJSON> enviarSolicitud() {
		try{
			BusinessDelegate bd = BusinessDelegate.getInstance();
			List<AgenciaDTO> agencias = bd.listarAgencias();
			List<AgenciaJSON> test=new ArrayList<AgenciaJSON>();
			for(AgenciaDTO agencia:agencias){
				AgenciaJSON jag = new AgenciaJSON(agencia.getIdAgencia(),agencia.getNombre(),agencia.getSolicitud().getIdSolicitud(),agencia.getSolicitud().getFechaCreacion(),agencia.getSolicitud().getEstado(),agencia.getCalle(),agencia.getNro(),agencia.getPiso(),agencia.getDepto(),agencia.getLocalidad(),agencia.getPais().getNombre());
				test.add(jag);
			}
			return test;
		}
		catch(Exception e){
			System.out.println("Fallo");
			e.printStackTrace();
			
		}
		return null;
	}
	
	@POST 
	@Path("/aprobarDesaprobarAgencia") 
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public String aprobarAgencia(EstadoSolicitudJSON jestado) {
		try {
			BusinessDelegate bd = BusinessDelegate.getInstance();

			bd.modificarEstadoSolicitud(jestado.getIdSolicitud(), jestado.getEstado());
			return "OK";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "Error";
	}
}
