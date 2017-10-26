package com.ofertaPaquetes.cliente;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.SolicitudDTO;
import com.ofertaPaquetes.dtos.TipoServicioDTO;

public class MiPrueba {
	
	public static void main(String[] args) throws NamingException {
		BusinessDelegate bd = BusinessDelegate.getInstance();
		
		/*Test Agencia*/
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
		
		/*Test Paquetes*/
		PaqueteDTO paquete = new PaqueteDTO("Paquete 1",new Date(),new Date(),"Descripcion paquete 1",220D,"",10,12,false,true);
		
		List<AgenciaDTO> agenciasDto =  bd.listarAgencias();
		
		AgenciaDTO agencia = new AgenciaDTO(agenciasDto.get(0).getIdAgencia());
		paquete.setAgencia(agencia);
		DestinoDTO destino = new DestinoDTO(1);
		paquete.setDestino(destino);
		
		List<ImagenDTO> imagenes = new ArrayList<ImagenDTO>();
		paquete.setImagenes(imagenes);
		List<TipoServicioDTO> servicios = new ArrayList<TipoServicioDTO>();
		paquete.setServicios(servicios);
		
		try{
			bd.nuevoPaquete(paquete);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		List<PaqueteDTO> paquetesDto = bd.listarPaquetes();
		PaqueteDTO paqdto = bd.obtenerPaquete(paquetesDto.get(0).getIdPaquete());
		
		System.out.println(paqdto.toString());
	}

}
