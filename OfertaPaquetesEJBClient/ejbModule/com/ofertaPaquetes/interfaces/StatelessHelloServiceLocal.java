package com.ofertaPaquetes.interfaces;

import javax.ejb.Local;

@Local
public interface StatelessHelloServiceLocal {
	public String sayHello(String name);
}
