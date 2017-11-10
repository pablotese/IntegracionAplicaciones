<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.ofertaPaquetes.dtos.AgenciaDTO"%>
<%@page import="com.ofertaPaquetes.dtos.ProvinciaDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agencias - Listado</title>
<script type="text/javascript" src="js/jquery/jquery-1.12.1.js"></script>
<script type="text/javascript" src="js/Bootstrap/bootstrap.js"></script>
<link type="text/css" href="css/Bootstrap/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="css/Site.css" rel="stylesheet" />
  
</head>

<nav id="menu" class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="menu-link navbar-brand" href="/OfertaPaquetesWebSite/Home"><span class="glyphicon glyphicon-home"></span></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a class="menu-link" href="/OfertaPaquetesWebSite/Index.jsp">Volver</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<body>

    <a class="btn btn-success btn-block" href="/OfertaPaquetesWebSite/Agencias?accion=crear"><span class="glyphicon glyphicon-plus"></span> Agregar agencia</a>
 
 
 

 
  <div class="container">
  <h2>Agencias</h2>
  <p>Listado de las agencias cargadas en el sistema.</p>                                                                                      
  <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th>#</th>
        <th style="display:none">ID</th>
        <th>Nombre</th>
        <th>Dirección</th>
        <th>Estado</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <% List<AgenciaDTO> lst = (List<AgenciaDTO>) request.getAttribute("listAgencias");
    		if(lst != null)
    		{
    		for(AgenciaDTO a : lst)
    		{
		%>
      <tr>
        <td></td>
        <td style="display:none"><%=a.getIdAgencia()%></td>
        <td><%=a.getNombre()%></td>
        <td><%=a.getCalle() + " " + a.getNro() + " " + a.getPiso() + a.getDepto() + ", " + a.getLocalidad() + ", " + a.getProvincia().getNombre()%></td>
        <td><%=a.isEstado()%></td>
        <td><a class="btn btn-primary" href="/OfertaPaquetesWebSite/Agencias?accion=editar&idAgencia=<%=a.getIdAgencia() %>" id="btnEdit"><span class="glyphicon glyphicon-eye-open"></span></a></td>
      </tr>
	<%}}%>
    </tbody>
  </table>
  </div>
</div>

</body>
</html>