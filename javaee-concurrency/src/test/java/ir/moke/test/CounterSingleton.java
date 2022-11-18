package ir.moke.test;

public class CounterSingleton {

    public static final CounterSingleton instance = new CounterSingleton();
    private long count = 0;

    private CounterSingleton() {
    }

    public synchronized long readCount() {
        System.out.println("Read count");
        sleep();
        return count;
    }

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
