package ir.moke.jaavee;

import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.atomic.AtomicReference;

@Path("/message")
public class JmsMessageSender {

    /**
     * Add this config to server.xml
     * <pre>
     *     <jmsQueueConnectionFactory jndiName="jms/JmsFactory">
     *         <properties.activemq/>
     *     </jmsQueueConnectionFactory>
     * </pre>
     */
//    @Inject
//    @JMSConnectionFactory(value = "jms/JmsFactory")
//    private JMSContext jmsContext;


    @Inject
    private Session session;

    @Inject
    private MessageProducer producer;

    @Inject
    private MessageConsumer consumer;

    @GET
    public Response sendMessage(@QueryParam("content") String content) {
        send(content);
        return Response.ok().build();
    }

    private void send(String content) {
        try {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(content);
            producer.send(textMessage);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
