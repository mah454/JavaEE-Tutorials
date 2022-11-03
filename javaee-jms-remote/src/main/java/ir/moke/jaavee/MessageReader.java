package ir.moke.jaavee;


import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@MessageDriven(name = "sample")
public class MessageReader implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            var text = textMessage.getText();
            System.out.println("Receive: " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
