<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Agencias - Eliminar</title>
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

<div class="container">
  <h2>Eliminar agencia.</h2>
  <p>Formulario para la eliminación de agencias del sistema.</p>  
  <div class="alert alert-danger alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    ¿Está seguro que desea eliminar esta agencia?
  </div>
  
  	<form id="frmDelete" method="POST" action="/OfertaPaquetesWebSite/Agencias?accion=eliminar&idAgencia=<%=request.getAttribute("idAgencia") %>">
  		<input type="hidden" id="idAgencia" value=<%=request.getAttribute("idAgencia") %>>
	  <div class="form-group">
	    <label for="Nombre">Nombre:</label>
	    <label type="text" id="nombre" name="nombre"><%=request.getAttribute("nombre")%></label>
	  </div>
	  <div class="form-group">
	    <label for="Direccion"">Direccion</label>
	    <hr />
	    <div class="row">
			<div class="col-lg-5 col-md-5 col-sm-5">
				<label>Calle:</label>
				<label id="calle" name="calle"><%=request.getAttribute("calle")%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Número:</label>
				<label id="numero" name="numero" ><%=request.getAttribute("numero")%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Piso:</label>
				<label id="piso" name="piso" type="number" maxlength=3><%=request.getAttribute("piso")%></label>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 col-xs-4">
				<label>Departamento:</label>
				<label id="dpto" name="dpto" type="text" maxlength=1><%=request.getAttribute("dpto")%></label>
			</div>
				
		</div>
		<div class="row">
			<div class="col-lg-5 col-md-5 col-sm-5">
				<label>Localidad:</label>
				<label id="localidad" name="localidad" type="text"><%=request.getAttribute("localidad")%></label>
			</div>
		</div>
	  </div>
	  
	   <div class="checkbox">
	   <label>Estado:</label>
	    <label><%=request.getAttribute("estado")%></label>
	  </div>
	  
	  <div class="row">
	  	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		  <a class="btn btn-danger" id="btnSubmit"><span class="glyphicon glyphicon-remove"></span> Eliminar</a>
	  	</div>
	  </div>
	  
	  </form>
</div>
<script>
	$("#btnSubmit").click(function(){
		$("#frmDelete").submit();
		})
</script>
</body>
</html>