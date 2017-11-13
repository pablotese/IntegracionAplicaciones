<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
    <%@page import="com.ofertaPaquetes.dtos.AgenciaDTO"  %>
    <%@page import="com.ofertaPaquetes.dtos.PaqueteServicioDTO"  %>
    <%@page import="com.ofertaPaquetes.dtos.DestinoDTO"  %>
    <%@page import="com.ofertaPaquetes.dtos.MedioDePagoDTO"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Paquetes - Alta</title>
<script type="text/javascript" src="js/jquery/jquery-1.12.1.js"></script>
<script type="text/javascript" src="js/Bootstrap/bootstrap.js"></script>
<link type="text/css" href="css/Bootstrap/bootstrap.css" rel="stylesheet" />

<script type="text/javascript" src="js/jquery_ui/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery_ui/jquery-ui-datetimepicker-es.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/core.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/localization/messages_es_AR.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/additional/accept.js"></script>
<script type="text/javascript" src="js/jquery_validation/src/additional/extension.js"></script>
<script type="text/javascript" src="js/multiselect/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="js/typeahead/typeahead_bundle.js"></script>



<link type="text/css" href="css/Site.css" rel="stylesheet" />
<link type="text/css" href="css/jquery_ui/jquery-ui.css" rel="stylesheet" />
<link type="text/css" href="css/jquery_ui/jquery-ui.structure.css" rel="stylesheet" />
<link type="text/css" href="css/jquery_ui/jquery-ui.theme.css" rel="stylesheet" />
<link type="text/css" href="css/multiselect/bootstrap-multiselect.css" rel="stylesheet" />

<style>
	#frmGrpServicios.form-group>span{
		width:100%;
		display:block;
	}
	
	#frmGrpServicios.form-group>span>.btn-group{
		width:100%;
	}
	
	#frmGrpServicios.form-group>span>.btn-group>button{
		width:100%;
	}
	
	#frmGrpMediosPagos.form-group>span{
		width:100%;
		display:block;
	}
	
	#frmGrpMediosPagos.form-group>span>.btn-group{
		width:100%;
	}
	
	#frmGrpMediosPagos.form-group>span>.btn-group>button{
		width:100%;
	}
	
	#scrollable-dropdown-menu .tt-menu {
  max-height: 150px;
  overflow-y: auto;
  width:100%;
  background:white;
  border:1px solid rgba(0, 0, 0, .15);
  border-radius:4px;
}

.tt-dataset{
	cursor:default;
}

.twitter-typeahead{
	width:100%;
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
      <a class="menu-link navbar-brand" href="/OfertaPaquetesWebSite/Home"><span class="glyphicon glyphicon-home"></span></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li><a class="menu-link" href="/OfertaPaquetesWebSite/Paquetes">Volver</a></li>  
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<body>
   <div class="container">
  <h2>Agregar paquetes</h2>
  <p>Formulario para la carga de paquetes al sistema.</p>                                                                                      
  
  <form id="frmPaquetes" method="post" enctype="multipart/form-data">
  <div class="form-group">
    <label>Nombre:</label>
    <input class="form-control" id="nombre" name="nombre" required>
  </div>
  <div class="form-group">
    <label for="descripcion">Descripción:</label>
    <textarea class="form-control" style="min-height:150px" name="descripcion" id="descripcion" required></textarea>
  </div>
  
  <div class="row">
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	  <div class="form-group">
		<label>Destino:</label>
		<%-- <select class="form-control" id="destino" name="destino" required>
			<option value="default">Ingrese un destino...</option>
			<% List<DestinoDTO> lstDest = (List<DestinoDTO>) request.getAttribute("listDestinos");
    		for(DestinoDTO d : lstDest)
    		{%>
			<option value=<%=d.getIdDestino() %>><%=d.getNombre() %></option>
			<%} %>
		  </select> --%>
		  
		  <input id="idDestino" name="idDestino" type="hidden" />
		  <div id="scrollable-dropdown-menu">
			  <input id="destino" name="destino" class="typeahead form-control" type="text" placeholder="Tipee un destino..." required>
			</div>
	  </div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	  <div class="form-group">
		<label>Agencia:</label>
		<select class="form-control" id="agencia" name="agencia" required>
			<option value="default">Seleccione...</option>
			<% List<AgenciaDTO> lst = (List<AgenciaDTO>) request.getAttribute("listAgencias");
    		for(AgenciaDTO p : lst)
    		{%>
				<option value=<%=p.getIdAgencia() %>><%=p.getNombre() %></option>
			<%} %>
		  </select>
	  </div>
  	</div>
  	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	  <div class="form-group" id="frmGrpServicios">
		<label>Servicios:</label>
		<select class="form-control" id="servicios" name="servicios" multiple="multiple" required>
		<% List<PaqueteServicioDTO> lstServ = (List<PaqueteServicioDTO>) request.getAttribute("listServicios");
    		for(PaqueteServicioDTO s : lstServ)
    		{%>
			<option value=<%=s.getIdServicio() %>><%=s.getNombreServicio() %></option>
			<%} %>
		  </select>
	  </div>
  	</div>
  	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	  <div class="form-group" id="frmGrpMediosPagos">
		<label>Medios de pago:</label>
		<select class="form-control" id="mediosPagos" name="mediosPagos" multiple="multiple" required>
		<% List<MedioDePagoDTO> lstMediosPagos = (List<MedioDePagoDTO>) request.getAttribute("listMediosPago");
    		for(MedioDePagoDTO m : lstMediosPagos)
    		{%>
			<option value=<%=m.getIdMedioDePago() %>><%=m.getNombre() %></option>
			<%} %>
		  </select>
	  </div>
  	</div>
  </div>
  
  <div class="form-group">
	<label>Foto:</label>
	 <input class="form-control" id="foto" name="foto" type="file" required>
  </div>
  <div class="row">
  
  
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
			<div class="form-group">
				<label>Fecha de Salida:</label>
				<input class="form-control" id="fechaSalida" name="fechaSalida" required>
			</div>
	</div>
	<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		<div class="form-group">
			<label>Fecha de Llegada:</label>
			<input class="form-control" id="fechaLlegada" name="fechaLlegada" required>
		</div>
	</div>
  
  </div>
  <div class="row">
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
		<div class="form-group">
			<label>Cupo:</label>
			<input class="form-control" id="cupo" name="cupo" type="number" required>
		</div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
		<div class="form-group">
			<label>Cant. de personas:</label>
			<input class="form-control" id="cantPersonas" name="cantPersonas" type="number" required />
		</div>
	</div>
	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
	  <div class="form-group">
		<label>Precio del paquete:</label>
		<input class="form-control" id="precio" name="precio" type="number" step="0.01" required />
	  </div>
	</div>
  </div>
  
  <div class="form-group">
	<label>Políticas de cancelación:</label>
	<textarea class="form-control" id="politicasCancelacion" name="politicasCancelacion" style="minheight:150px" required></textarea>
  </div>
  
	<div class="checkbox">
		<label><input id="estado" name="estado" type="checkbox" checked="checked" disabled>Habilitado</label>
	</div>
  
  <button type="submit" class="btn btn-primary center-block">Guardar</button>
</form>
</div>

<script>
	
  $( function() {
   $.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg !== value;
 }, "Value must not equal arg.");
  
  $("#frmPaquetes").validate({
  rules: {
   destino: { valueNotEquals: "default" },
   servicios: { valueNotEquals: "default" },
   agencia : { valueNotEquals: "default" },
   foto:{
	   	required: true,
	   	extension: "jpg|png"
	   }
  },
  messages: {
   destino: { valueNotEquals: "Este campo es requerido" },
   servicios: { valueNotEquals: "Este campo es requerido" },
   agencia: { valueNotEquals: "Este campo es requerido" },
   foto:{
	   	extension:"Formato no soportado"
	   }
  }  
 });
  $( "#fechaSalida" ).datepicker();
  $( "#fechaLlegada" ).datepicker();
  
  $("#fechaSalida").change(function(){
	$( "#fechaLlegada" ).datepicker( "option", "minDate", $( "#fechaSalida" ).datepicker( "getDate" ));
  })
  } );

  $("#servicios").multiselect({
	  nonSelectedText: 'Seleccione...'
	  });
  $("#mediosPagos").multiselect({
	  nonSelectedText: 'Seleccione...'
	  });

		var jsonDest = <%= request.getAttribute("destJson") %>

		var destinos = new Bloodhound({
			identify: function(obj) { return obj.idDestino; },
			  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('nombreDestino'),
			  queryTokenizer: Bloodhound.tokenizers.whitespace,
			  // `states` is an array of state names defined in "The Basics"
			  local: jsonDest
			});

			// initialize the bloodhound suggestion engine
			destinos.initialize();


			$('#destino').typeahead({
			  hint: true,
			  highlight: true,
			  minLength: 1
			},
			{
			  name: 'states',
			  source: destinos,
			  display:'nombreDestino',
			  limit:10
			});

			$('#destino').bind('typeahead:select', function(ev, suggestion) {
			  $("#idDestino").val(suggestion.idDestino);
			});
				

		// Set up variables to store the selection and the original 
		// value.
		var selected      = null;
		var originalVal   = null;

		// When the typeahead becomes active reset these values.
		$("#destino").on("typeahead:active", function(aEvent) {
		   selected       = null;
		   originalVal    = $("#destino").typeahead("val");
		})

		// When a suggestion gets selected save that
		$("#destino").on("typeahead:select", function(aEvent, aSuggestion) {
		   selected = aSuggestion;
		});

		// Once user leaves the component and a change was registered
		// check whether or not something was selected. If not reset to
		// the original value.
		$("#destino").on("typeahead:change", function(aEvent, aSuggestion) {
		   if (!selected) {
			   $("#destino").typeahead("val", originalVal);
		   }

		   // Do something with the selected value here as needed...
		});  

		
  </script>
  </body>
</html>