package ir.moke.javaee;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

@Singleton
public class Counter {

    private long count = 0;

    @Lock(value = LockType.READ)
    public long readCount() {
        System.out.println("Read count");
        sleep();
        return count;
    }

    @Lock(value = LockType.READ)
    public void setCount() {
        System.out.println(count + "  Add count");
        sleep();
        this.count++;
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
