package com.bank.abc.util;

import com.bank.abc.model.Counters;
import com.bank.abc.model.PremiumCustomer;
import com.bank.abc.model.RegularCustomer;
import com.bank.abc.model.Token;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class TokenUtils {

    public static boolean isCounterAvailable(List<Counters> counters) {
        for (Counters counter : counters) {
            if (counter.size() < 2) {
                return true;
            }
        }
        return false;
    }

    public static Counters getAvailableQueue(List<Counters> counters) {
        for (Counters counter : counters) {
            if (counter.size() < 2) {
                return counter;
            }
        }
        return null;
    }

    public static boolean isValidTokenToServe(List<Counters> counters, Token tokenToUpdate) {
        for (Counters counter : counters) {
            if (counter.peek() != null) {
                Token token = (Token) counter.peek();
                if (token.getId().equals(tokenToUpdate.getId()))
                    return true;
            }
        }
        return false;
    }

    public static void assignTokenToQueue(Token token, List<Counters> counters) {
        PremiumCustomer premiumCustomer = PremiumCustomer.getPremiumCustomer();
        RegularCustomer regularCustomer = RegularCustomer.getRegularCustomer();
        if (TokenUtils.isCounterAvailable(counters)) {
            Counters queueAssigned = TokenUtils.getAvailableQueue(counters);
            queueAssigned.offer(token);
            token.setCounterAssigned(queueAssigned.getCounterId());
        } else {
            if (token.getPriority().equals(PriorityType.PREMIUM)) {
                premiumCustomer.offer(token);
            } else {
                regularCustomer.offer(token);
            }
        }
    }

    public static void dequeToken(Token token, List<Counters> counters) {
        for (Counters counter : counters) {
            if (counter.getCounterId().equals(token.getCounterAssigned())) {
                counter.poll();
            }
        }
    }

    public static void forwardToken(Token token, List<Counters> counters) {
        //In case of forwarding, next counter number should be specified in action items
        //like "Forwarded to c2"
        String actionItem = token.getActionItems();
        if (!ObjectUtils.isEmpty(actionItem)) {
            for (Counters counter : counters) {
                if (counter.getCounterId().toLowerCase().equals(actionItem.substring(actionItem.length() - 2).toLowerCase())) {
                    counter.offer(token);
                    return;
                }
            }
        }
    }
}
