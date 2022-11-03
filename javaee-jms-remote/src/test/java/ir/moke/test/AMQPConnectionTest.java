package ir.moke.test;

import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import jakarta.jms.*;
import jakarta.naming.InitialContext;

public class AMQPConnectionTest {
    public static void main(final String[] args) throws Exception {
        Connection connection = null;
        try {
            // Step 1. Directly instantiate the JMS Queue object.
            Queue queue = ActiveMQJMSClient.createQueue("sample");

            // Starting with Artemis 1.0.1 you can just use the URI to instantiate the object directly
            ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Step 4.Create a JMS Connection
            connection = cf.createConnection();

            // Step 5. Create a JMS Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Step 6. Create a JMS Message Producer
            MessageProducer producer = session.createProducer(queue);

            // Step 7. Create a Text Message
            TextMessage message = session.createTextMessage("This is a text message");

//            System.out.println("Sent message: " + message.getText());

            // Step 8. Send the Message
//            producer.send(message);

            // Step 9. Create a JMS Message Consumer
            MessageConsumer messageConsumer = session.createConsumer(queue);

            // Step 10. Start the Connection
            connection.start();

            // Step 11. Receive the message
            TextMessage messageReceived = (TextMessage) messageConsumer.receive(5000);

            System.out.println(">> Received message: " + messageReceived.getText());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
