package ir.moke.test;

public class CounterTest {
    public static void main(String[] args) {

        Thread t1 = new Thread(CounterSingleton.instance::readCount);
        Thread t2 = new Thread(CounterSingleton.instance::readCount);

        t1.start();
        t2.start();
    }
}
