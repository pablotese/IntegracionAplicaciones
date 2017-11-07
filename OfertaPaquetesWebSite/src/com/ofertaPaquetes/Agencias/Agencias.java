package com.ofertaPaquetes.Agencias;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.PaisDTO;
import com.ofertaPaquetes.dtos.ProvinciaDTO;

/**
 * Servlet implementation class agencias
 */
@WebServlet("/Agencias")
public class Agencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaisDTO dtoPais = new PaisDTO(1,"Argentina");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Agencias() {
        super();
        BusinessDelegate bd = BusinessDelegate.getInstance();
        bd.cargarDatosIniciales();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		RequestDispatcher rd = null;
		BusinessDelegate bd = BusinessDelegate.getInstance();
		
		if(accion == null)
		{
			
			List<AgenciaDTO> lst = bd.listarAgencias();
			request.setAttribute("listAgencias", lst);
			
			rd = request.getRequestDispatcher("/views/agencias/list.jsp");
		}
		
		if(accion != null)
		{
			if(accion.equals("crear"))
			{
				List<ProvinciaDTO> provs = bd.getListadoProvincias();
				if(provs == null)
					provs = new ArrayList<ProvinciaDTO>();
				
				request.setAttribute("listProvincias",provs);
				rd = request.getRequestDispatcher("/views/agencias/create.jsp");
			}
			if(accion.equals("editar"))
			{
				String idAgencia = request.getParameter("idAgencia");
				if(idAgencia != null && !idAgencia.isEmpty()) {
					AgenciaDTO dto = bd.obtenerAgencia(Integer.parseInt(idAgencia));
					request = setViewModel(request,dto);						
				}
				
				List<ProvinciaDTO> provs = bd.getListadoProvincias();
				if(provs == null)
					provs = new ArrayList<ProvinciaDTO>();
				
				request.setAttribute("listProvincias",provs);
				
				rd = request.getRequestDispatcher("/views/agencias/edit.jsp");
			}
			if(accion.equals("eliminar"))
			{
				String idAgencia = request.getParameter("idAgencia");
				if(idAgencia != null && !idAgencia.isEmpty()) {
					AgenciaDTO dto = bd.obtenerAgencia(Integer.parseInt(idAgencia));
					request = setViewModel(request,dto);						
				}
				rd = request.getRequestDispatcher("/views/agencias/delete.jsp");
			}
		}
		rd.forward(request, response);
	}
	
	private HttpServletRequest setViewModel(HttpServletRequest request, AgenciaDTO viewModel) {
		request.setAttribute("idAgencia", viewModel.getIdAgencia());
		request.setAttribute("nombre", viewModel.getNombre());
		request.setAttribute("email", viewModel.getEmail());
		request.setAttribute("calle", viewModel.getCalle());
		request.setAttribute("numero", viewModel.getNro());
		request.setAttribute("piso", viewModel.getPiso());
		request.setAttribute("dpto", viewModel.getDepto());
		request.setAttribute("localidad", viewModel.getLocalidad());
		request.setAttribute("estado", viewModel.isEstado());
		request.setAttribute("idProvincia", viewModel.getProvincia().getIdProvincia());
		
		return request;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgenciaDTO viewModel = new AgenciaDTO(0);
		BusinessDelegate bd = BusinessDelegate.getInstance();
		
		try {
			String idAgencia = request.getParameter("idAgencia"); 
			String accion = request.getParameter("accion"); 
			if(accion != null)
			{
				if(accion.equals("crear"))
				{
					//Nueva agencia
					viewModel.setPais(dtoPais);
					
					viewModel.setNombre(request.getParameter("nombre"));
					viewModel.setEmail(request.getParameter("email"));
					viewModel.setCalle(request.getParameter("calle"));
					viewModel.setNro(Integer.parseInt(request.getParameter("numero")));
					viewModel.setPiso(request.getParameter("piso"));
					viewModel.setDepto(request.getParameter("dpto"));
					viewModel.setLocalidad(request.getParameter("localidad"));
					viewModel.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
					
					List<ProvinciaDTO> provs = bd.getListadoProvincias();
					ProvinciaDTO dtoProv = new ProvinciaDTO();
					for(ProvinciaDTO p: provs)
					{
						if(p.getIdProvincia() == Integer.parseInt(request.getParameter("provincia")))
						{
							dtoProv = p;
						}
					}
					
					viewModel.setProvincia(dtoProv);
					
					//TEST: get servicios
					//getServicios();
					
					//Llamada a servicio de BO para solicitud de ID de agencia global.
					String serviceResponse = postAgencias(viewModel);
				
					int idBO = Integer.parseInt(serviceResponse);
					viewModel.setIdAgenciaBO(idBO);
					
					bd.nuevaAgencia(viewModel);
				}
				if(accion.equals("eliminar") && idAgencia != null && !idAgencia.isEmpty())
				{
					bd.eliminarAgencia(Integer.parseInt(idAgencia));
				}
				if(accion.equals("editar") && idAgencia != null && !idAgencia.isEmpty())
				{
					viewModel.setIdAgencia(Integer.parseInt(idAgencia));
					viewModel.setNombre(request.getParameter("nombre"));
					viewModel.setEmail(request.getParameter("email"));
					viewModel.setCalle(request.getParameter("calle"));
					viewModel.setNro(Integer.parseInt(request.getParameter("numero")));
					viewModel.setPiso(request.getParameter("piso"));
					viewModel.setDepto(request.getParameter("dpto"));
					viewModel.setLocalidad(request.getParameter("localidad"));
					viewModel.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
					
					
					List<ProvinciaDTO> provs = bd.getListadoProvincias();
					if(provs == null)
					{
						provs = new ArrayList<ProvinciaDTO>();
					}
					
					ProvinciaDTO dtoProv = new ProvinciaDTO();
					for(ProvinciaDTO p: provs)
					{
						if(p.getIdProvincia() == Integer.parseInt(request.getParameter("provincia")))
						{
							dtoProv = p;
						}
					}
					
					request.setAttribute("idProvincia", dtoProv.getIdProvincia());
					viewModel.setProvincia(dtoProv);
					viewModel.setPais(dtoPais);
					
		            bd.modificarAgencia(viewModel);	
				}
			}
			
			response.sendRedirect("/OfertaPaquetesWebSite/Agencias");
		} catch (Exception e) {
			//TODO: SHOW MODAL ERROR OR ERROR PAGE
			e.printStackTrace();
		}
	}
	
	private String postAgencias(AgenciaDTO dto) throws Exception
	{
		URL url = new URL("http://localhost:8080/enviarSolicitud/rest/service/EnviarSolicitud");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		
		//Armo el json String
		JsonObjectBuilder paqueteJsonBuilder = Json.createObjectBuilder()
	   			.add("detalle",dto.getNombre())
	   					.add("tipo","Agencia");
		   				
		   	JsonObject paqueteJson = paqueteJsonBuilder.build();
	        StringWriter stringWriter = new StringWriter();
	        
	        JsonWriter writer = Json.createWriter(stringWriter);
	        writer.writeObject(paqueteJson);
	        writer.close();
	        
	        String json = paqueteJson.toString();
		
		
		
		IOUtils.write(json, urlConnection.getOutputStream());
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexi�n: " + urlConnection.getResponseCode());
		}
		
		String response = IOUtils.toString(urlConnection.getInputStream());
		return response;
	}
	
	private void getServicios() throws Exception
	{
		URL url = new URL("http://192.168.0.108:8080/TPO_BO_WEB/rest/ServiciosBO/GetTiposServicios");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexi�n: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
	
	

}
