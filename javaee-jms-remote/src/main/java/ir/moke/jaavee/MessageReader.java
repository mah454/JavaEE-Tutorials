package ir.moke.jaavee;


import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

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
