package ir.moke.javaee.api;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Observes;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

@Path("/notification")
@Singleton
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class NotificationResources {

    @Context
    private Sse sse;
    private SseBroadcaster sseBroadcaster;

    @PostConstruct
    public void init() {
        this.sseBroadcaster = sse.newBroadcaster();
    }

    @GET
    @Path("/engine")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Lock(LockType.READ)
    public void engineNotification(@Context SseEventSink sseEventSink) {
        sseBroadcaster.register(sseEventSink);
    }

    @Lock
    public void checkEvent(@Observes String message) {
        OutboundSseEvent outboundSseEvent = sse.newEventBuilder().data(message).build();
        sseBroadcaster.broadcast(outboundSseEvent);
    }
}
