package com.bank.abc.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class PremiumCustomer<Token> extends LinkedList<Token> {
    private static PremiumCustomer premiumCustomer = new PremiumCustomer();

    public static PremiumCustomer getPremiumCustomer() {
        return premiumCustomer;
    }

    private PremiumCustomer() {
    }

    @Override
    public Object clone() {
        try {
             throw new CloneNotSupportedException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Object readResolve() {
        return premiumCustomer;
    }
}
