<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agencias - Editar</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<h2>Editar agencias</h2>
  <p>Formulario para la edición de agencias del sistema.</p>                                                                                      
  <form id="frmAgencias" method="post" action="/OfertaPaquetesWebSite/Agencias?accion=editar&idAgencia=<%=request.getAttribute("idAgencia") %>">
  		<input type="hidden" id="idAgencia" name="idAgencia" value=<%=request.getAttribute("idAgencia")%>>
	  <div class="form-group">
	    <label for="Nombre">Nombre:</label>
	    <input type="text" class="form-control" id="nombre" name="nombre" value=<%=request.getAttribute("nombre") %>>
	  </div>
	  <div class="form-group">
	    <label for="Direccion"">Direccion</label>
	    <hr />
	    <div class="row">
			<div class="col-lg-5 col-md-5 col-sm-5">
				<label>Calle:</label>
				<input class="form-control" id="calle" name="calle" type="text" value=<%=request.getAttribute("calle") %>>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Número:</label>
				<input class="form-control" id="numero" name="numero" type="text" value=<%=request.getAttribute("numero") %>>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Piso:</label>
				<input class="form-control" id="piso" name="piso" type="number" maxlength=3 value=<%=request.getAttribute("piso") %>>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Departamento:</label>
				<input class="form-control" id="dpto" name="dpto" type="text" maxlength=1 value=<%=request.getAttribute("dpto") %>>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>País:</label>
				<input class="form-control" id="pais" name="pais" type="text" maxlength=1>
			</div>
				
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5 col-sm-5">
				<label>Localidad:</label>
				<input class="form-control" id="localidad" name="localidad" type="text" value=<%=request.getAttribute("localidad") %>>
			</div>
		</div>
	  </div>
	  
	  <div class="form-group">
	  	<label>Condiciones de rechazo</label>
	  	<textarea class="form-control" id="condiciones" name="condiciones"></textarea>
	  </div>
	  
	   <div class="checkbox">
    	<label><input type="checkbox" id="estado" name="estado" value=<%=request.getAttribute("estado") %>> Habilitado</label>
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
<script>
	$("#btnSubmit").click(function(){
		$("#frmAgencias").submit();
		})
</script>
</body>
</html>