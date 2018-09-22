package ir.moke.javaee.bank;

import javax.enterprise.inject.Produces;

public class BankFactory {

    @Produces
    @BankProducer(BankType.MELLAT)
    public Bank getBankMellat() {
        return new BankMellat();
    }

    @Produces
    @BankProducer(BankType.SADERAT)
    public Bank getBankSaderat() {
        return new BankSaderat();
    }
}
