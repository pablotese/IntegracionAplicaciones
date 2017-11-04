package src;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ofertaPaquetes.businessDelegate.BusinessDelegate;
import com.ofertaPaquetes.dtos.AgenciaDTO;
import com.ofertaPaquetes.dtos.DestinoDTO;
import com.ofertaPaquetes.dtos.ImagenDTO;
import com.ofertaPaquetes.dtos.PaqueteDTO;
import com.ofertaPaquetes.dtos.TipoServicioDTO;



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
		
		if(accion != null && accion.equals("crear"))
		{
			List<AgenciaDTO> agencias = bd.listarAgencias();
			request.setAttribute("listAgencias", agencias);
			request.setAttribute("listServicios", getServiciosList());
			request.setAttribute("listDestinos", getDestinosList());
			
			rd = request.getRequestDispatcher("/views/paquetes/create.jsp");
		}
		else
		{
			rd = request.getRequestDispatcher("/views/paquetes/list.jsp");	
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		 try
		 {
			 BusinessDelegate bd = BusinessDelegate.getInstance();
			 
			 @SuppressWarnings("unused")
			 java.util.Date test = (java.util.Date) formatter.parse(request.getParameter("fechaSalida"));
			 List<TipoServicioDTO> lstServicios = new ArrayList<TipoServicioDTO>();
			 String[] servicios = request.getParameterValues("servicios");
			 
			 //Armo lista de servicio asociados al paquete.
			 for(String s : servicios)
			 {
				 TipoServicioDTO serv = new TipoServicioDTO("hardCode","hardCode");
				 serv.setIdTipoServicio(Integer.parseInt(s));
				 lstServicios.add(serv);
			 }
			 
			 //"Obtengo" el destino
			 DestinoDTO dest = new DestinoDTO(Integer.parseInt(request.getParameter("destino")));
			 
			 //Obtengo la agencia
			 AgenciaDTO agen = bd.obtenerAgencia(Integer.parseInt(request.getParameter("agencia")));
			 
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
		
			 //Seteo servicios, destino, agencias e imagen
			 nuevoPaquete.setServicios(lstServicios);
			 nuevoPaquete.setDestino(dest);
			 nuevoPaquete.setAgencia(agen);
			 List<ImagenDTO> imagenes = new ArrayList<ImagenDTO>();
			 //nuevoPaquete.setImagen(imagenes.get(0).ge);
			 //TODO: guardar una URL a la imagen, que guardamos en un server local primero.
			 //Cuando se ejecuta el envío por JMS la ponemos en un server remoto
			 nuevoPaquete.setImagen("Fotito.JPG");
			 
			 //Persistencia
			 bd.nuevoPaquete(nuevoPaquete);
		 }
		 catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }
		
		response.sendRedirect("/OfertaPaquetesWebSite/Paquetes");
	}
	
	private List<TipoServicioDTO> getServiciosList()
	{
		List<TipoServicioDTO> ret = new ArrayList<TipoServicioDTO>();
		
		TipoServicioDTO serv = new TipoServicioDTO("Servicio_1","Desc_1");
		serv.setIdTipoServicio(1);
		ret.add(serv);
		
		serv = new TipoServicioDTO("Servicio_3","Desc_3");
		serv.setIdTipoServicio(3);
		ret.add(serv);
		return ret;
	}
	
	private List<DestinoDTO> getDestinosList()
	{
		List<DestinoDTO> ret = new ArrayList<DestinoDTO>();
		DestinoDTO dest = new DestinoDTO();
		dest.setIdDestino(1);
		dest.setNombre("Destino_1");
		ret.add(dest);
		
		dest = new DestinoDTO();
		dest.setIdDestino(2);
		dest.setNombre("Destino_2");
		ret.add(dest);
		
		return ret;
	}

}
