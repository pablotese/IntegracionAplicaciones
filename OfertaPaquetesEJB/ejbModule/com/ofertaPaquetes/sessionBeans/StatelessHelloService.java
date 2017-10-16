package com.ofertaPaquetes.sessionBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ofertaPaquetes.interfaces.StatelessHelloServiceLocal;
import com.ofertaPaquetes.interfaces.StatelessHelloServiceRemote;

/**
 * Session Bean implementation class StatelessHelloService
 */
@Stateless
@LocalBean
public class StatelessHelloService implements StatelessHelloServiceRemote, StatelessHelloServiceLocal {

    /**
     * Default constructor. 
     */
    public StatelessHelloService() {
        // TODO Auto-generated constructor stub
    }
    
    public String sayHello(String name){
    	return "Hola "+ name;
    }

}