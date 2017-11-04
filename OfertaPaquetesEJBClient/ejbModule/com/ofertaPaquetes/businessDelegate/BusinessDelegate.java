package com.ofertaPaquetes.businessDelegate;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.MedioDePagoDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;
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
			System.out.println("Error al crear agencia");
			e.printStackTrace();
			
		}
	}
	
	public void modificarAgencia(AgenciaDTO agenciaDto){
		try{
			stub.modificarAgencia(agenciaDto);
		}
		catch(Exception e){
			System.out.println("Error al modificar agencia");
			e.printStackTrace();
			
		}
	}
	public void eliminarAgencia(int idAgencia){
		try{
			stub.eliminarAgencia(idAgencia);
		}
		catch(Exception e){
			System.out.println("Error al eliminar agencia");
			e.printStackTrace();
			
		}
	}
	
	
	public List<AgenciaDTO> listarAgenciasPorEstado(boolean estado){
		try{
			return stub.listarAgenciasPorEstado(estado);
		}
		catch(Exception e){
			System.out.println("Error al listar agencias por estado");
			e.printStackTrace();
			
		}
		return null;
	}
	
	public List<AgenciaDTO> listarAgencias(){
		try{
			return stub.listarAgencias();
		}
		catch(Exception e){
			System.out.println("Error al listar agencias");
			e.printStackTrace();
			
		}
		return null;
	}
	
	public AgenciaDTO obtenerAgencia(int idAgencia){
		try{
			return stub.obtenerAgencia(idAgencia);
		}
		catch(Exception e){
			System.out.println("Error al obtener agencia");
			e.printStackTrace();
		}
		return null;
	}
	
	public void nuevoPaquete(PaqueteDTO paqueteDto){
		try{
			stub.nuevoPaquete(paqueteDto);
		}
		catch(Exception e){
			System.out.println("Error al crear paquete");
			e.printStackTrace();
			
		}
	}
	public void modificarPaquete(PaqueteDTO paqueteDto){
		try{
			stub.modificarPaquete(paqueteDto);
		}
		catch(Exception e){
			System.out.println("Error al modificar paquete");
			e.printStackTrace();
			
		}
		
	}
	public void eliminarPaquete(int idPaquete){
		try{
			stub.eliminarPaquete(idPaquete);
		}
		catch(Exception e){
			System.out.println("Error al eliminar paquete");
			e.printStackTrace();
			
		}
	}
	
	public List<PaqueteDTO> listarPaquetes(){
		try{
			return stub.listarPaquetes();
		}
		catch(Exception e){
			System.out.println("Error al listar paquetes");
			e.printStackTrace();
			
		}
		return null;	
	}
	
	public PaqueteDTO obtenerPaquete(int idPaquete){
		try{
			return stub.obtenerPaquete(idPaquete);
		}
		catch(Exception e){
			System.out.println("Error al obtener paquete");
			e.printStackTrace();
			
		}
		return null;
	}
	
	public List<ProvinciaDTO> getListadoProvincias(){
		try{
			return stub.getListadoProvincias();
		}
		catch(Exception e){
			System.out.println("Error al obtener listado de provincias");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MedioDePagoDTO> getListadoMediosDePago(){
		try{
			return stub.getListadoMediosDePago();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void cargarDatosIniciales(){
		try{
			stub.cargarDatosIniciales();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}


