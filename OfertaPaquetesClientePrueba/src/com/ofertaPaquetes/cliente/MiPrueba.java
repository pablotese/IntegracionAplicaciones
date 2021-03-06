package com.ofertaPaquetes.cliente;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.PaqueteServicioDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;

public class MiPrueba {
	
	public static void main(String[] args) throws NamingException {
		
		BusinessDelegate bd = BusinessDelegate.getInstance();
		bd.cargarDatosIniciales();
		//prueba();
		//prueba();
	}
	
	public static void prueba(){
		BusinessDelegate bd = BusinessDelegate.getInstance();
		/*Test Agencia*/
		PaisDTO pais= new PaisDTO(1,"Argentina");
				
		AgenciaDTO ag = new AgenciaDTO("Prueba Agencia",false, "calle",123,"1","C","capital Federal",pais, new Date());
		ag.setEmail("test@test.com");
		ag.setProvincia(new ProvinciaDTO(1,"Buenos Aires"));
		
		ag.setIdAgenciaBO(23);
		try {	
			bd.nuevaAgencia(ag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Test Paquetes*/
		//PaqueteDTO paquete = new PaqueteDTO("Paquete 1",new Date(),new Date(),"Descripcion paquete 1",220D,"",10,12,false,true);
	
		List<AgenciaDTO> agenciasDto =  bd.listarAgencias();
		AgenciaDTO agencia = new AgenciaDTO(agenciasDto.get(0).getIdAgencia());
		DestinoDTO destino = new DestinoDTO(1, "Miami");
		
		//Los tipos de servicio vienen de un WS de BO
		List<PaqueteServicioDTO> servicios = new ArrayList<PaqueteServicioDTO>(); 
		servicios.add(new PaqueteServicioDTO(1, "Internet"));
		servicios.add(new PaqueteServicioDTO(2, "Re pileta")); 
		
		PaqueteDTO paquete = new PaqueteDTO("Paquete 1 Miami", new Date(2017, 11,1), new Date(2017, 12,1), 
				"Paquete 1 viaje a Miami", (double) 12000, "No cancelable", 40, 2, true, true, "Miami.jpg", agencia, destino, servicios);

		try{
			bd.nuevoPaquete(paquete);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		List<PaqueteDTO> paquetesDto = bd.listarPaquetes();
		PaqueteDTO paqdto = bd.obtenerPaquete(paquetesDto.get(0).getIdPaquete());
		
		System.out.println(paqdto.toString());
		System.out.println("Test finished!!!!!!!");


	}


}
