package com.bank.abc.util;

import com.bank.abc.model.Counters;
import com.bank.abc.model.Customer;
import com.bank.abc.model.Token;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TokenStub {
    private static Map<Long, Token> tokens = new HashMap<>();
    private static Long tokenIndex = 0L;

    static List<Counters> counters = new ArrayList<>();

    static {

        Counters counter1 = new Counters("COUNTER-1", "Deposits");
        Counters counter2 = new Counters("COUNTER-2", "Drafts");
        Counters counter3 = new Counters("COUNTER-3", "Loans");

        counters.add(counter1);
        counters.add(counter2);
        counters.add(counter3);
        mapCustomerToQueue(counters);
    }

    private static void mapCustomerToQueue(List<Counters> counters) {
        List<Customer> customers = CustomerStub.getAllCustomers();

        for (Customer customer : customers) {
            tokens.put(++tokenIndex, new Token(tokenIndex, customer.getCustomerType(), Status.OPEN, customer.getCustomerId()));
            TokenUtils.assignTokenToQueue(tokens.get(tokenIndex), counters);
        }
    }

    public static List<Counters> getQueues() {
        return counters;
    }

    public static Map<Long, Token> getTokens() {
        return tokens;
    }

    public static Token create(Token token) {
        token.setId(++tokenIndex);
        tokens.put(tokenIndex, token);
        return token;
    }

    public static Token get(Long id) {
        return tokens.get(id);
    }

    public static Token update(Long id, Token token) {
        tokens.put(id, token);
        return token;
    }

    public static Token delete(Long id) {
        return tokens.remove(id);
    }

    public static Map<Long, Token> getAllTokens() {
        return tokens;
    }
}
