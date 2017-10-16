package com.ofertaPaquetes.cliente;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ofertaPaquetes.interfaces.AdministradorPaqueteRemote;


public class MiPrueba {
	

	public static void main(String[] args) throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "OfertaPaquetesEAR"; // Nombre del módulo EAR
		final String ejbModuleName = "OfertaPaquetesEJB"; // Nombre del módulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
		/*final String ejbClassName = "StatelessHelloService"; // Nombre corto del EJB
		final String fullInterfaceName = StatelessHelloServiceRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" +
		distinctName + "/" + ejbClassName + "!" + fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		StatelessHelloServiceRemote mbr = (StatelessHelloServiceRemote)context.lookup(lookupName);
		System.out.println("Respuesta: " + mbr.sayHello("Mr Bean"));*/
		
		final String ejbClassNameE = "AdministradorPaquete"; // Nombre corto del EJB
		final String fullInterfaceNameE = AdministradorPaqueteRemote.class.getName();
		String lookupNameE = "ejb:" + earName + "/" + ejbModuleName + "/" + 
			   distinctName + "/" + ejbClassNameE + "!" + fullInterfaceNameE + "?stateful";
		System.out.println("Conectando a " + lookupNameE);
		AdministradorPaqueteRemote modServidor = (AdministradorPaqueteRemote) context.lookup(lookupNameE);
		modServidor.nuevoPaquete();

	}

}
