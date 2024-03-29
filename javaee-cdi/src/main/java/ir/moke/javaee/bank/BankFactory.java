package ir.moke.javaee.bank;

import ir.moke.javaee.bank.impl.BankMellat;
import ir.moke.javaee.bank.impl.BankSaderat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class BankFactory {

    @Produces
    @Bank(BankType.MELLAT)
    public BankInterface getBankMellat() {
        return new BankMellat();
    }

    @Produces
    @Bank(BankType.SADERAT)
    public BankInterface getBankSaderat() {
        return new BankSaderat();
    }
}
