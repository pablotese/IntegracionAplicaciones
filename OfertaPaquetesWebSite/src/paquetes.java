

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.paquete;



/**
 * Servlet implementation class paquetes
 */
@WebServlet("/paquetes")
public class paquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<paquete> _paquetes = new ArrayList<paquete>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paquetes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		RequestDispatcher rd = null;
		
		request.setAttribute("listPaquetes", _paquetes);
		if(accion != null && accion.equals("crear"))
		{
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
		paquete nuevoPaquete = new paquete();
		nuevoPaquete.Nombre = request.getParameter("nombre");
		nuevoPaquete.Destino = request.getParameter("destino");
		nuevoPaquete.FechaSalida = request.getParameter("fechaSalida");
		nuevoPaquete.FechaRegreso = request.getParameter("fechaRegreso");
		nuevoPaquete.Cupo = Integer.parseInt(request.getParameter("cupo"));
		nuevoPaquete.Estado = request.getParameter("estado");
		
		_paquetes.add(nuevoPaquete);
		
		response.sendRedirect("/OfertaPaquetesWebSite/paquetes");
	}

}
