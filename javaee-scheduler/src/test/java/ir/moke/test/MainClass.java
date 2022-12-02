package ir.moke.test;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainClass {

    public static synchronized void process() {
        System.out.println(Thread.currentThread().getId() + "    -> " + LocalDateTime.now() + "  CDI With ManagedScheduledExecutorService Executed");
    }


    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                process();
            }
        };

        Timer timer = new Timer(false);
        timer.scheduleAtFixedRate(timerTask, 2000, 5000);
    }
}
