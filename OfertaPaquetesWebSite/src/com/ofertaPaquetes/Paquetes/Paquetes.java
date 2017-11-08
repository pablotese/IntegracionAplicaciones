package com.ofertaPaquetes.Paquetes;


import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.MedioDePagoDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.PaqueteServicioDTO;



/**
 * Servlet implementation class paquetes
 */
@WebServlet("/Paquetes")
public class Paquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<PaqueteDTO> _paquetes = new ArrayList<PaqueteDTO>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paquetes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		RequestDispatcher rd = null;
		BusinessDelegate bd = BusinessDelegate.getInstance();
		_paquetes = bd.listarPaquetes(); 
		request.setAttribute("listPaquetes", _paquetes);
		
		try
		{
		if(accion != null)
		{
			if(accion.equals("crear")) {
				try {
					request.setAttribute("listAgencias", bd.listarAgencias());
					request.setAttribute("listServicios", getServiciosList());
					request.setAttribute("listDestinos", bd.listarDestinos());
					request.setAttribute("listMediosPago", bd.getListadoMediosDePago());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				rd = request.getRequestDispatcher("/views/paquetes/create.jsp");
			}
			
			if(accion.equals("editar") && request.getParameter("idPaquete") != null)
			{
				try {
				
					int idPaquete = Integer.parseInt(request.getParameter("idPaquete"));
					PaqueteDTO dto = bd.obtenerPaquete(idPaquete);
					
					if(dto != null)
					{
						List<PaqueteServicioDTO> servicios = getServiciosList();
						List<MedioDePagoDTO> mediosPago = bd.getListadoMediosDePago();
						
						request.setAttribute("listAgencias", bd.listarAgencias());
						request.setAttribute("listServicios", servicios);
						request.setAttribute("listDestinos", bd.listarDestinos());
						request.setAttribute("listMediosPago", mediosPago);
						
						request.setAttribute("idPaquete", idPaquete);
						request.setAttribute("cantPersonas", dto.getCantPersonas());
						request.setAttribute("cupo", dto.getCupo());
						request.setAttribute("descripcion", dto.getDescripcion());
						request.setAttribute("nombre", dto.getNombre());
						request.setAttribute("fechaDesde", dto.getFechaDesde());
						request.setAttribute("fechaHasta", dto.getFechaHasta());
						request.setAttribute("politicasCancelacion", dto.getPoliticasCancelacion());
						request.setAttribute("precio", dto.getPrecio());
						request.setAttribute("idAgencia", dto.getAgencia().getIdAgencia());
						request.setAttribute("idDestino", dto.getDestino().getIdDestino());
						
						//Seteo medios de pago
						List<Integer> lstMediosPagos = new ArrayList<Integer>(); 
						
						for(MedioDePagoDTO dtomp : dto.getMediosDePago())
						{
							lstMediosPagos.add(dtomp.getIdMedioDePago());
						}
						
						request.setAttribute("listMediosPagosElegidos", lstMediosPagos);
						
						//Seteo tipos de servicio.
						List<Integer> lstServicios = new ArrayList<Integer>(); 
						
						for(PaqueteServicioDTO dtos : dto.getServicios())
						{
							lstServicios.add(dtos.getIdServicio());
						}
						
						request.setAttribute("listServiciosElegidos", lstServicios);
						
						rd = request.getRequestDispatcher("/views/paquetes/edit.jsp");
					}
					else {throw new Exception("Error al obtener paquete");}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					request.setAttribute("errorMsg", "Lo sentimos, ha sucedido un error al intentar realizar su petición.");
					rd = request.getRequestDispatcher("/error.jsp");
				}
			}
		}
		else
		{
			rd = request.getRequestDispatcher("/views/paquetes/list.jsp");	
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("errorMsg", "Lo sentimos, ha sucedido un error al intentar realizar su petición.");
			rd = request.getRequestDispatcher("/error.jsp");
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		 RequestDispatcher rd = null;
		 
		 try
		 {
			 BusinessDelegate bd = BusinessDelegate.getInstance();
			 
			 @SuppressWarnings("unused")
			 java.util.Date test = (java.util.Date) formatter.parse(request.getParameter("fechaSalida"));
			 List<PaqueteServicioDTO> lstSelectedServicios = new ArrayList<PaqueteServicioDTO>();
			 List<MedioDePagoDTO> lstSelectedMediosPago = new ArrayList<MedioDePagoDTO>();
			 String[] servicios = request.getParameterValues("servicios");
			 String[] mediosPago = request.getParameterValues("mediosPagos");
						 
			 PaqueteDTO nuevoPaquete = new PaqueteDTO(request.getParameter("nombre"),
												formatter.parse(request.getParameter("fechaSalida")),
												formatter.parse(request.getParameter("fechaLlegada")),
												request.getParameter("descripcion"),
												Double.parseDouble(request.getParameter("precio")),
												request.getParameter("politicasCancelacion"),
												Integer.parseInt(request.getParameter("cupo")),
												Integer.parseInt(request.getParameter("cantPersonas")),
												false,true
												);
			 
			//Armo lista de servicio asociados al paquete.
			 //Obtengo la lista de servicios
			 List<PaqueteServicioDTO> lstServicios = getServiciosList();
			 
			 for(String s : servicios)
			 {
				 PaqueteServicioDTO serv = getServicio(Integer.parseInt(s),lstServicios);
				 lstSelectedServicios.add(serv);
			 }
			 
			 //Armo lista de medios de pago asociados al paquete
			 for(String m : mediosPago)
			 {
				 MedioDePagoDTO dto = new MedioDePagoDTO();
				 int idMedioPago = Integer.parseInt(m);
				 dto = bd.obtenerMedioPago(idMedioPago);
				 lstSelectedMediosPago.add(dto);
			 }
			 
			 //"Obtengo" el destino
			 DestinoDTO dest = new DestinoDTO(Integer.parseInt(request.getParameter("destino")));
			 
			 //Obtengo la agencia
			 AgenciaDTO agen = bd.obtenerAgencia(Integer.parseInt(request.getParameter("agencia")));
		
			 //Seteo servicios, destino, agencias e imagen
			 nuevoPaquete.setServicios(lstSelectedServicios);
			 nuevoPaquete.setDestino(dest);
			 nuevoPaquete.setAgencia(agen);
			 nuevoPaquete.setMediosDePago(lstSelectedMediosPago);
			 List<ImagenDTO> imagenes = new ArrayList<ImagenDTO>();
			 //nuevoPaquete.setImagen(imagenes.get(0).ge);
			 //TODO: guardar una URL a la imagen, que guardamos en un server local primero.
			 //Cuando se ejecuta el envï¿½o por JMS la ponemos en un server remoto
			 nuevoPaquete.setImagen("Fotito.JPG");
			 
			 //Persistencia
			 bd.nuevoPaquete(nuevoPaquete);
		 }
		 catch(Exception ex)
		 {
			ex.printStackTrace();
			request.setAttribute("errorMsg", "Lo sentimos, ha sucedido un error al intentar realizar su petición.");
			rd = request.getRequestDispatcher("/views/error.jsp");
			rd.forward(request, response);
		 }
		
		response.sendRedirect("/OfertaPaquetesWebSite/Paquetes");
	}
	
	private List<PaqueteServicioDTO> getServiciosList() throws Exception
	{
		List<PaqueteServicioDTO> ret = new ArrayList<PaqueteServicioDTO>();
		
		//Llamada a BO
		URL url = new URL("http://localhost:8080/enviarSolicitud/rest/service/GetServicios");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexiï¿½n: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
		
		JsonReader jsonReader = Json.createReader(new StringReader(response));
		JsonArray jsonArray = jsonReader.readArray();
		jsonReader.close();
		
		for(JsonValue json : jsonArray)
		{
			JsonObject obj = (JsonObject)json;
			PaqueteServicioDTO dto = new PaqueteServicioDTO();
			dto.setNombreServicio(obj.getString("nombre"));
			dto.setIdServicio(obj.getInt("id"));
			ret.add(dto);
		}
		
		return ret;
	}
	
	private PaqueteServicioDTO getServicio(int idServicio, List<PaqueteServicioDTO> lst)
	{
		for(PaqueteServicioDTO dto : lst)
		{
			if(dto.getIdServicio() == idServicio)
			{
				return dto;
			}
		}
		
		return null;
	}

}
