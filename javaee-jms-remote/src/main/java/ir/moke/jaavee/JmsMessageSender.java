package ir.moke.jaavee;

import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
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
