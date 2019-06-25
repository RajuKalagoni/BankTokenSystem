package com.bank.abc.model;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class RegularCustomer<Token> extends LinkedList<Token> {
    private static RegularCustomer regularCustomer = new RegularCustomer();

    public static RegularCustomer getRegularCustomer() {
        return regularCustomer;
    }

    private RegularCustomer() {
    }

    protected Object readResolve() {
        return regularCustomer;
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
}
