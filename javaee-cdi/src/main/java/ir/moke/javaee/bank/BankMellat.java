package ir.moke.javaee.bank;

public class BankMellat implements Bank {
    @Override
    public void payment(int cash) {
        System.out.println("Receive cash : " + cash);
    }
}
