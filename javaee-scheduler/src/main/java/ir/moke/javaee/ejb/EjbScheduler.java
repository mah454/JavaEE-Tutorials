package ir.moke.javaee.ejb;

import jakarta.ejb.*;

import java.time.LocalDateTime;

@Singleton
@Startup
public class EjbScheduler {

    @Lock(LockType.READ)
    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void execute() {
        System.out.println(LocalDateTime.now() + "  EJB Scheduler Executed");
    }
}
