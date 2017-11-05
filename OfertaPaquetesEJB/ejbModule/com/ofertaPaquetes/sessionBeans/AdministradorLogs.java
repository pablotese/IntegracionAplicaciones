package com.ofertaPaquetes.sessionBeans;

import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.apache.commons.io.IOUtils;

import com.ofertaPaquetes.util.Properties;

@Stateless
@LocalBean
public class AdministradorLogs {
	
	public AdministradorLogs() {
		super();
	}
	
	public void enviarLog(String plataformaEnvia, String plataformaRecibe, long time, String servicio,
			String observacion){
		try{
			
			URL url = new URL(Properties.URL_LOG);
			System.out.println(Properties.URL_LOG);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type","application/json");

		   	JsonObjectBuilder logJsonBuilder = Json.createObjectBuilder()
		   			.add("plataformaEnvia",plataformaEnvia)
		   			.add("plataformaRecibe",plataformaRecibe)
		   			.add("fecha", time)
		   			.add("servicio",servicio)
		   			.add("observacion", observacion);
			   	
		   	
		   	JsonObject logJson = logJsonBuilder.build();
	        StringWriter stringWriter = new StringWriter();
	        JsonWriter writer = Json.createWriter(stringWriter);
	        writer.writeObject(logJson);
	        writer.close();
	       
	        IOUtils.write(stringWriter.getBuffer().toString(),urlConnection.getOutputStream());

			if(urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}
			
	       String response = 	IOUtils.toString(urlConnection.getInputStream());
			
		   System.out.println("Respuesta: "+ response);
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
}
