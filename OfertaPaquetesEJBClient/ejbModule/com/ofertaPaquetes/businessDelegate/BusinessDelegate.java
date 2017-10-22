package com.ofertaPaquetes.businessDelegate;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.interfaces.FacadeEJBRemote;

public class BusinessDelegate {

	private static BusinessDelegate instancia = null;
	private static FacadeEJBRemote stub = null;

	private BusinessDelegate() {
		try {
			stub = getStub();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static BusinessDelegate getInstance() {
		if (instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}

	private static FacadeEJBRemote getStub() throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);

		final String earName = "OfertaPaquetesEAR"; // Nombre del módulo EAR
		final String ejbModuleName = "OfertaPaquetesEJB"; // Nombre del módulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
		final String ejbClassName = "FacadeEJB"; // Nombre corto del EJB. Es quien implementa la interface 'FacadeEJBRemote'
		final String fullInterfaceName = FacadeEJBRemote.class.getName();

		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + 
				ejbClassName + "!" + fullInterfaceName;

		System.out.println("Conectando a " + lookupName);

		return (FacadeEJBRemote) context.lookup(lookupName);
	}

	public void nuevaAgencia(AgenciaDTO agenciaDto) throws Exception {
		try{
			stub.nuevaAgencia(agenciaDto);
		}
		catch(Exception e){
			System.out.println("ERROR");
			e.printStackTrace();
			
		}
	}
}

