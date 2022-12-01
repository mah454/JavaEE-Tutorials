package ir.moke.javaee;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@Singleton
@Startup
public class NotificationProducer {

    @Inject
    private Event<String> notificationEvent;

    @Schedule(second = "*/5",minute = "*",hour = "*",persistent = false)
    public void processNotification() {
        System.out.println("Hello");
        notificationEvent.fire("Hello");
    }
}
