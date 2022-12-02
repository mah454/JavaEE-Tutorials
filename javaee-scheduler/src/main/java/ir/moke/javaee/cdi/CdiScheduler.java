package ir.moke.javaee.cdi;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class CdiScheduler {

    @Resource
    private ManagedScheduledExecutorService mxs;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object obj) {
        mxs.scheduleAtFixedRate(this::process, 0, 5, TimeUnit.SECONDS);
    }

    public void process() {
        System.out.println(Thread.currentThread().getId() + "    -> " + LocalDateTime.now() + "  CDI With ManagedScheduledExecutorService Executed");
    }
}
