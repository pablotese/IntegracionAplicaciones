package com.ofertaPaquetes;

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
public class JAXRSClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/RestExample/rest/service/hola");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
		
		url = new URL("http://localhost:8080/RestExample/rest/service/saludo");
		urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "text/plain");
		IOUtils.write("Juanito", urlConnection.getOutputStream());
		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
}