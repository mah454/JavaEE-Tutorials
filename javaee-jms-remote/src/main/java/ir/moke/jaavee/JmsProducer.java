package ir.moke.jaavee;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.*;

@ApplicationScoped
public class JmsProducer {

    /**
     * Add this config to server.xml
     * <pre>
     *     <connectionFactory jndiName="jms/factory">
     *         <properties.activemq/>
     *     </connectionFactory>
     * </pre>
     */
    @Resource(lookup = "jms/factory")
    private ConnectionFactory factory;

    @Resource(name = "jms/queue/input")
    private Queue input;

    @Resource(name = "jms/queue/output")
    private Queue output;

    @Produces
    @Named("jms-connection")
    public Connection getConnection() throws JMSException {
        return factory.createConnection();
    }

    @Produces
    @Named("jms-session")
    public Session getSession(@Named("jms-connection") Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Produces
    public MessageProducer getProducer(@Named("jms-session") Session session) throws JMSException {
        return session.createProducer(input);
    }

    @Produces
    public MessageConsumer getConsumer(@Named("jms-session") Session session) throws JMSException {
        return session.createConsumer(output);
    }
}
