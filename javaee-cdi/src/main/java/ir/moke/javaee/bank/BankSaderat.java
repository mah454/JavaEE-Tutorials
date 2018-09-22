package ir.moke.javaee.bank;

public class BankSaderat implements Bank {
    @Override
    public void payment(int cash) {
        System.out.println("Receive cash : " + cash);
    }
}
