package ir.moke.javaee;

import jakarta.ejb.Stateful;

@Stateful
public class Processor {

    public void process() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
