package com.ofertaPaquetes.cliente;


import javax.naming.NamingException;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;

public class MiPrueba {
	
	public static void main(String[] args) throws NamingException {
		BusinessDelegate bd = BusinessDelegate.getInstance();
		PaisDTO pais= new PaisDTO(1,"Argentina");
		AgenciaDTO ag = new AgenciaDTO("Prueba",false, "calle",123,"1","C","capital Federal",pais);
		
		try {
			bd.nuevaAgencia(ag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
