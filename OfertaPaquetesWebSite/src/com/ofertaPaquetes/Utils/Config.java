package com.ofertaPaquetes.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	public static String getBOServiciosURL() {
		String ret = "";
		Properties prop = new Properties();
		InputStream input = null;

		try {
			
			input = new FileInputStream("META-INF/site.properties");
			prop.load(input);

			ret = prop.getProperty("url_bo_get_servicios");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	public static String getBOAgenciasURL() {
		String ret = "";
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("META-INF/site.properties");
			prop.load(input);

			ret = prop.getProperty("url_bo_get_solicitud_agencia");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}

}
