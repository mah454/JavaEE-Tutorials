package ir.moke.javaee.bank.impl;

import ir.moke.javaee.bank.BankInterface;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

@Singleton
public class BankMellat implements BankInterface {

    @PostConstruct
    public void init() {
        System.out.println("BankMellat initialized");
    }

    @Override
    public void payment(int cash) {
        System.out.println("Receive cash : " + cash);
    }
}
