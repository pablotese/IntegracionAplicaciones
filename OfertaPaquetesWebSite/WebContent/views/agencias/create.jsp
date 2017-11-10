<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.ofertaPaquetes.dtos.ProvinciaDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Agencias - Alta</title>
<script type="text/javascript" src="js/jquery/jquery-1.12.1.js"></script>
<script type="text/javascript" src="js/Bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/core.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/localization/messages_es_AR.js"></script>
<link type="text/css" href="css/Bootstrap/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="css/Site.css" rel="stylesheet" />

<style>
  #menu{
    background: black!important;
  }
  .menu-link{
    color:white!important;
  }
  
</style>
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
      <a class="menu-link navbar-brand" href="/OfertaPaquetesWebSite/Index.jsp"><span class="glyphicon glyphicon-home"></span></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li><a class="menu-link" href="/OfertaPaquetesWebSite/Agencias">Volver</a></li>  
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<body>
   <br>
   <br>
  <div class="container">
	  <h2>Agregar agencias</h2>
	  <p>Formulario para la carga de agencias al sistema.</p>                                                                                      
	  <form id="frmAgencias" method="post">
	  	<div class="row">
	  		<div class="col-lg-6 col-md-6 col-sm-6">
	  		<div class="form-group">
			    <label for="Nombre">Nombre:</label>
			    <input type="text" class="form-control" id="nombre" name="nombre" required>
			  </div>
	  		</div>
	  		<div class="col-lg-6 col-md-6 col-sm-6">
	  		<div class="form-group">
			    <label for="email">Correo electrónico:</label>
			    <input type="email" class="form-control" id="email" name="email" required>
			  </div>
	  		</div>
	  	</div>
		  <div class="form-group">
		    <label for="Direccion"">Direccion</label>
		    <hr />
		    <div class="row">
				<div class="col-lg-5 col-md-5 col-sm-5">
					<label>Calle:</label>
					<input class="form-control" id="calle" name="calle" type="text" required>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
					<label>Número:</label>
					<input class="form-control" id="numero" name="numero" type="text" required>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
					<label>Piso:</label>
					<input class="form-control" id="piso" name="piso" type="number" maxlength=3>
				</div>
				<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
					<label>Departamento:</label>
					<input class="form-control" id="dpto" name="dpto" type="text" maxlength=1>
				</div>
					
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<label>Localidad / Barrio:</label>
					<input class="form-control" id="localidad" name="localidad" type="text" required>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<label>Provincia:</label>
					<select class="form-control" id="provincia" name="provincia" required>
						<option value="default">Seleccione...</option>
					<% List<ProvinciaDTO> provs = (List<ProvinciaDTO>) request.getAttribute("listProvincias");
						for(ProvinciaDTO p : provs)
						{%>
						<option value=<%=p.getIdProvincia() %>><%=p.getNombre() %></option>
					<%} %>
					</select>
				</div>
			</div>
		  </div>
		  
		   <div class="checkbox">
		    <label><input type="checkbox" id="estado" name="estado" checked="checked" disabled> Estado</label>
		  </div>
		  
		  <div class="row">
		  	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-4 col-lg-offset-3">
			  <a class="btn btn-primary" id="btnSubmit"><span class="glyphicon glyphicon-ok"></span> Guardar</a>
		  	</div>
		  	<div class="col-xs-6 col-sm-6 col-md-6 col-lg-2">
			  <a class="btn btn-danger" href="/OfertaPaquetesWebSite/Agencias"><span class="glyphicon glyphicon-remove"></span> Cancelar</a>
		  	</div>
		  </div>
	</form>
</div>
</body>

<script>
$(function(){
	  $.validator.addMethod("valueNotEquals", function(value, element, arg){
		  return arg !== value;
		 }, "Value must not equal arg.");
		  
		  $("#frmAgencias").validate({
		  rules: {
		   provincia: { valueNotEquals: "default" },
		   email: {required: true, email:true}
		  },
		  messages: {
			  provincia: { valueNotEquals: "Este campo es requerido" }
		  }  
		 });
})

	$("#btnSubmit").click(function(){
		$("#frmAgencias").submit();
		})
</script>
</html>