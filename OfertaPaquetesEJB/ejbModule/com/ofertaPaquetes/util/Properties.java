package com.ofertaPaquetes.util;

public class Properties {

	
	public static final String DESTINATION = "jms/queue/ofertasPaquete";//"java:jboss/exported/jms/queue/testQueue2";;//"java:jboss/exported/jms/queue/testQueue2";
	//public static final String DESTINATION_2 = "jms/queue/ofertasPaquete";//java:jboss/exported/jms/queue/testQueue2";//"java:/myJmsTest/MyQueue";
	//public static final String CONNECTION_FACTORY="java:jboss/exported/jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_2="jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_USER = "hornetq";//"myUser";
	public static final String CONNECTION_FACTORY_PASSWORD = "hornetq";//"myPassword";
	public static final String PROVIDER_URL="http-remoting://192.168.0.102:8080";//PW
	//public static final String SECURITY_PRINCIPAL="vanesa";//"myUser";
	//public static final String SECURITY_CREDENTIALS="Vanesa14";//"myPassword";
	//public static final String PUBLIC_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\imgs\\";
	//public static final String LOCAL_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\VaneEar.ear\\OfertaPaquetesWebSite.war\\fotos\\";
	public static final String IP_LOCAL = "192.168.0.104";	
	
	
	// Properties Mensajeria local
	/*
	public static final String DESTINATION = "java:jboss/exported/jms/queue/testQueue2";//"java:jboss/exported/jms/queue/testQueue2";;//"java:jboss/exported/jms/queue/testQueue2";
	public static final String DESTINATION_2 = "jms/queue/testQueue2";//java:jboss/exported/jms/queue/testQueue2";//"java:/myJmsTest/MyQueue";
	public static final String CONNECTION_FACTORY="java:jboss/exported/jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_2="jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_USER = "vanesa";//"myUser";
	public static final String CONNECTION_FACTORY_PASSWORD = "Vanesa14";//"myPassword";
	public static final String PROVIDER_URL="http-remoting://localhost:8080";
	public static final String SECURITY_PRINCIPAL="vanesa";//"myUser";
	public static final String SECURITY_CREDENTIALS="Vanesa14";//"myPassword";
	public static final String PUBLIC_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\imgs\\";
	public static final String LOCAL_IMG_REPO = "C:\\Users\\minnie\\Documents\\Facu\\IA\\IA\\wildfly-9.0.1.Final\\standalone\\deployments\\VaneEar.ear\\OfertaPaquetesWebSite.war\\fotos\\";
	public static final String IP_LOCAL = "192.168.1.1";*/
	//Properties Mensajeria TP*/
	/*
	public static final String DESTINATION = "jms/queue/ofertasPaquete";
	public static final String CONNECTION_FACTORY="jms/RemoteConnectionFactory";
	public static final String CONNECTION_FACTORY_USER = "hornetq";
	public static final String CONNECTION_FACTORY_PASSWORD = "hornetq";
	public static final String PROVIDER_URL="tap-remoting://192.168.0.100:8080";
	public static final String SECURITY_PRINCIPAL="hornetq";
	public static final String SECURITY_CREDENTIALS="hornetq";
	*/
	
	//Properties rest web services - de BO
	public static final String URL_LOG="http://192.168.0.100:8080/TPO_BO_WEB/rest/ServiciosBO/RegistrarLog";
	public static final String URL_POST_AGENCIA = "http://192.168.0.100:8080/TPO_BO_WEB/rest/ServiciosBO/EnviarSolicitud";
	
	//public static final String URL_LOG="http://localhost:8080/enviarSolicitud/rest/service/RegistrarLog";
	//public static final String URL_POST_AGENCIA = "http://localhost:8080/enviarSolicitud/rest/service/EnviarSolicitud";
	
}
