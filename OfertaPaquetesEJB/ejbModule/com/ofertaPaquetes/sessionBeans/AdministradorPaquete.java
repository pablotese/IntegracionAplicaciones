package com.ofertaPaquetes.sessionBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.TipoServicioDTO;
import com.ofertaPaquetes.entities.Agencia;
import com.ofertaPaquetes.entities.Destino;
import com.ofertaPaquetes.entities.Imagen;
import com.ofertaPaquetes.entities.Paquete;
/**
 * Session Bean implementation class AdministradorTareas
 */
@Stateless
@LocalBean
public class AdministradorPaquete{

	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;
    
	public AdministradorPaquete() {
        // TODO Auto-generated constructor stub
    }
	
	public void nuevoPaquete(PaqueteDTO paquete){
		try{
			Paquete paq = new Paquete(paquete.getNombre(),paquete.getFechaDesde(), paquete.getFechaHasta(),
					paquete.getDescripcion(), paquete.getPrecio(), paquete.getPoliticasCancelacion(), 
					paquete.getCupo(), paquete.getCantPersonas(), paquete.isEstado(), paquete.isNuevaOferta());
			
			
						
			//Servicios
			
			/*La agencia ya existe*/
			Agencia agencia = manager.find(Agencia.class, paquete.getAgencia().getIdAgencia());
			paq.setAgencia(agencia);
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
			
			manager.persist(paq);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al crear paquete");
		}
	}
	
	public void modificarPaquete(PaqueteDTO paquete){
		try{
			Paquete paq = manager.find(Paquete.class,paquete.getIdPaquete());
			paq.setAgencia(manager.find(Agencia.class,paquete.getAgencia().getIdAgencia()));
			paq.setCantPersonas(paquete.getCantPersonas());
			paq.setCupo(paquete.getCupo());
			paq.setDescripcion(paquete.getDescripcion());
			paq.setEstado(paquete.isEstado());
			paq.setFechaDesde(paquete.getFechaDesde());
			paq.setFechaHasta(paquete.getFechaHasta());
			paq.setNombre(paquete.getNombre());
			paq.setPoliticasCancelacion(paquete.getPoliticasCancelacion());
			paq.setPrecio(paquete.getPrecio());
			
			/*Servicios*/
			
			
			/*El destino ya existe*/
			Destino destino = manager.find(Destino.class,paquete.getDestino().getIdDestino());
			paq.setDestino(destino);
		
			
			manager.merge(paq);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al actualizar Paquete");
		}
	}
	
	public void eliminarPaquete(int idPaquete){
		try{
			Paquete paquete = manager.find(Paquete.class, idPaquete);
			manager.remove(paquete);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al eliminar Paquete");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PaqueteDTO> listarPaquetes(){
		try{
			List<PaqueteDTO> lista = new ArrayList<PaqueteDTO>();
		
			List<Paquete> paquetes = (List<Paquete>) manager.createQuery(" FROM Paquete").getResultList();
		
			for(Paquete paq:paquetes){
				PaqueteDTO dto = new PaqueteDTO(paq.getNombre(),paq.getFechaDesde(),paq.getFechaHasta(),paq.getDescripcion(),paq.getPrecio(),paq.getPoliticasCancelacion(),paq.getCupo(),paq.getCantPersonas(),paq.isEstado(),paq.isNuevaOferta());
				dto.setIdPaquete(paq.getIdPaquete());
				PaisDTO pais = new PaisDTO(paq.getAgencia().getPais().getIdPais(), paq.getAgencia().getPais().getNombre());
				AgenciaDTO agencia = new AgenciaDTO(paq.getAgencia().getNombre(), paq.getAgencia().isEstado(),paq.getAgencia().getCalle(),paq.getAgencia().getNro(),paq.getAgencia().getPiso(),paq.getAgencia().getDepto(),paq.getAgencia().getLocalidad(),pais, paq.getAgencia().getFechaCreacion());
				
				if(paq.getDestino()!=null){
					DestinoDTO destino = new DestinoDTO(paq.getDestino().getIdDestino(),paq.getDestino().getNombre());
					dto.setDestino(destino);
				}
				
				dto.setAgencia(agencia);
				
				/*no cargo imagenes y tipo de servicios ya que esto es solo una lista*/
				
				lista.add(dto);
			}
			return lista;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public PaqueteDTO obtenerPaquete(int idPaquete){
		try{
			Paquete paquete = manager.find(Paquete.class, idPaquete);
			PaqueteDTO paq = new PaqueteDTO(paquete.getNombre(),paquete.getFechaDesde(),paquete.getFechaHasta(),paquete.getDescripcion(),paquete.getPrecio(),paquete.getPoliticasCancelacion(),paquete.getCupo(),paquete.getCantPersonas(),paquete.isEstado(),paquete.isNuevaOferta());
			System.out.println(paquete.toString());
			
			AgenciaDTO agencia = new AgenciaDTO(paquete.getAgencia().getIdAgencia());
			paq.setAgencia(agencia);
			
			if(paquete.getDestino()!=null){
				DestinoDTO destino = new DestinoDTO(paquete.getDestino().getIdDestino(),paquete.getDestino().getNombre());
				paq.setDestino(destino);
			}
			
			
			/*Servicios*/
			
			return paq;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al obtener paquete");
		}
		return null;
	}

}
