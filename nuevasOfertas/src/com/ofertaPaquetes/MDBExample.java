package com.ofertaPaquetes;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MDBExample
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/testQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		}, 
		mappedName = "java:/jms/queue/testQueue")
public class MDBExample implements MessageListener {

    /**
     * Default constructor. 
     */
    public MDBExample() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;

            try {
                System.out.println(
                  String.format(
                    "A message was found! ^__^ It is: '%s'",
                    textMessage.getText()
                  )
                );
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

}
