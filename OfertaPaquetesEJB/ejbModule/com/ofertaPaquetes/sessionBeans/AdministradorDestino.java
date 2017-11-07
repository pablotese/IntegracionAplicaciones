package com.ofertaPaquetes.sessionBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.MedioDePagoDTO;
import com.ofertaPaquetes.entities.Destino;
import com.ofertaPaquetes.entities.MedioDePago;
import com.ofertaPaquetes.entities.Paquete;

/**
 * Session Bean implementation class AdministradorDestino
 */
@Stateless
@LocalBean
public class AdministradorDestino {

	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;
	
    public AdministradorDestino() {
        // TODO Auto-generated constructor stub
    }
    
    public List<DestinoDTO> listarDestinos()
    {
		List<DestinoDTO> lst = new ArrayList<DestinoDTO>();
		List<Destino> destinos = (List<Destino>) manager.createQuery(" FROM Destino").getResultList();
    	
		for(Destino d : destinos)
		{
			DestinoDTO dto = new DestinoDTO();
			dto.setIdDestino(d.getIdDestino());
			dto.setNombre(d.getNombre());
			
			lst.add(dto);
		}
		
    	return lst;
    }
    
    public DestinoDTO obtenerDestino(int idDestino)
    {
    	try{
			Destino dest = manager.find(Destino.class, idDestino);
			DestinoDTO dto = new DestinoDTO();
			dto.setIdDestino(dest.getIdDestino());
			dto.setNombre(dest.getNombre());

			return dto;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al obtener destino");
		}
		return null;
    }
    	

}
