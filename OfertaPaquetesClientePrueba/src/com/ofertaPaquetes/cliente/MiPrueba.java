package com.ofertaPaquetes.cliente;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.interfaces.FacadeEJBRemote;

public class MiPrueba {
	
	public static void main(String[] args) throws NamingException {
		BusinessDelegate bd = BusinessDelegate.getInstance();
			
		AgenciaDTO ag = new AgenciaDTO("Prueba",false);
		try {
			bd.nuevaAgencia(ag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
