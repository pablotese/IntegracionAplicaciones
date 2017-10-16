package com.ofertaPaquetes;
import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/send")
public class ProducerServlet extends HttpServlet {
    @Resource(lookup = "java:jboss/exported/jms/RemoteConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/testQueue")
    Destination destination;


    @Override
    protected void doGet(
      HttpServletRequest req,
      HttpServletResponse resp
    ) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        try {
            //Authentication info can be omitted if we are using in-vm
            QueueConnection connection = (QueueConnection) connectionFactory.createConnection("myUser","myPassword");

            try {
                QueueSession session =
                  connection.createQueueSession(
                    false,
                    Session.AUTO_ACKNOWLEDGE
                  );

                try {
                    MessageProducer producer =
                      session.createProducer(destination);

                    try {
                        TextMessage message =
                          session.createTextMessage(
                            "Hello, world! ^__^"
                          );

                        producer.send(message);

                        writer.println(
                          "Message sent! ^__^"
                        );
                    } finally {
                        producer.close();
                    }
                } finally {
                    session.close();
                }

            } finally {
                connection.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace(writer);
        }
    }
}