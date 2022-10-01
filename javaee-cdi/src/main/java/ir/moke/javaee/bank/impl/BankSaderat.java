package ir.moke.javaee.bank.impl;

import ir.moke.javaee.bank.BankInterface;

import javax.annotation.PostConstruct;

public class BankSaderat implements BankInterface {

    @PostConstruct
    public void init() {
        System.out.println("BankSaderat initialized");
    }

    @Override
    public void payment(int cash) {
        System.out.println("Receive cash : " + cash);
    }
}
