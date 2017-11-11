package com.ofertaPaquetes.util;

public class Properties {
	// Properties Mensajeria local
	public static final String DESTINATION = "java:jboss/exported/jms/queue/testQueue2";
	public static final String DESTINATION_2 = "jms/queue/testQueue2";
	public static final String CONNECTION_FACTORY="java:jboss/exported/jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_2="jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_USER = "myUser";//"myUser";
	public static final String CONNECTION_FACTORY_PASSWORD = "myPassword";//"myPassword";
	public static final String PROVIDER_URL="http-remoting://localhost:8080";
	public static final String SECURITY_PRINCIPAL="myUser";
	public static final String SECURITY_CREDENTIALS="myPassword";
	public static final String PUBLIC_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\imgs\\";
	public static final String LOCAL_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\VaneEar.ear\\OfertaPaquetesWebSite.war\\fotos\\";
	//Properties Mensajeria TP
	/*
	public static final String DESTINATION = "jms/queue/ofertasPaquete";
	public static final String CONNECTION_FACTORY="jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_USER = "hornetq";
	public static final String CONNECTION_FACTORY_PASSWORD = "hornetq";
	public static final String PROVIDER_URL="http-remoting://192.168.0.100:8080";
	public static final String SECURITY_PRINCIPAL="hornetq";
	public static final String SECURITY_CREDENTIALS="hornetq";
	*/
	
	//Properties rest web services
	public static final String URL_LOG="http://localhost:8080/enviarSolicitud/rest/service/RegistrarLog";
	public static final String URL_POST_AGENCIA = "http://localhost:8080/enviarSolicitud/rest/service/EnviarSolicitud";
	
}
