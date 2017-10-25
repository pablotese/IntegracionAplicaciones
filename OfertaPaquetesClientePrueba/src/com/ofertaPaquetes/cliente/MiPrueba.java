package com.ofertaPaquetes.cliente;


import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.SolicitudDTO;

public class MiPrueba {
	
	public static void main(String[] args) throws NamingException {
		BusinessDelegate bd = BusinessDelegate.getInstance();
		PaisDTO pais= new PaisDTO(1,"Argentina");
		AgenciaDTO ag = new AgenciaDTO("Prueba",false, "calle",123,"1","C","capital Federal",pais);
		SolicitudDTO solicitud = new SolicitudDTO(new Date(), "PENDIENTE");
		ag.setSolicitud(solicitud);
		try {
			bd.nuevaAgencia(ag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<AgenciaDTO> agencias = bd.listarAgenciasPorEstado("PENDIENTE");
		if(agencias!=null){
			for(AgenciaDTO dto:agencias){
				System.out.println(dto.toString());
			}
		}

	}

}
