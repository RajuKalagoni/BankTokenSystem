package com.bank.abc.service;

import com.bank.abc.dao.TokenDAO;
import com.bank.abc.model.Counters;
import com.bank.abc.model.PremiumCustomer;
import com.bank.abc.model.RegularCustomer;
import com.bank.abc.model.Token;
import com.bank.abc.util.PriorityType;
import com.bank.abc.util.Status;
import com.bank.abc.util.TokenStub;
import com.bank.abc.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenDAO tokenDAO;

    @Override
    public Map<String, List<String>> list() {
        List<Counters> counters = tokenDAO.getQueues();
        Map<String, List<String>> tokens = new HashMap<>();
        String prefix;
        for (Counters counter : counters) {
            List<String> counterTokens = new ArrayList<>();
            for (int person = 0; person < counter.size(); person++) {
                Token currentToken = ((Token) counter.get(person));
                if (currentToken.getPriority().equals(PriorityType.PREMIUM))
                    prefix = "PREMIUM-";
                else
                    prefix = "REGUAL-";
                counterTokens.add(prefix + currentToken.getId());
            }
            tokens.put(counter.getCounterId(), counterTokens);
        }
        return tokens;
    }

    @Override
    public Token updateToken(Long id, Token token) {
        PremiumCustomer premiumCustomer = PremiumCustomer.getPremiumCustomer();
        RegularCustomer regularCustomer = RegularCustomer.getRegularCustomer();

        tokenDAO.updateToken(id, token);
        if (!(token.getStatus().equals(Status.OPEN))) {
            TokenUtils.dequeToken(token, tokenDAO.getQueues());
            if (token.getStatus().equals(Status.FORWARDED)) {
                TokenUtils.forwardToken(token, tokenDAO.getQueues());
            } else {
                if (!premiumCustomer.isEmpty()) {
                    TokenUtils.assignTokenToQueue((Token) premiumCustomer.poll(), tokenDAO.getQueues());
                } else if (!regularCustomer.isEmpty()) {
                    TokenUtils.assignTokenToQueue((Token) regularCustomer.poll(), tokenDAO.getQueues());
                }
            }
        }
        return token;
    }

    @Override
    public Token createToken(Token token) {
        Token createdToken = tokenDAO.createToken(token);

        TokenUtils.assignTokenToQueue(createdToken, tokenDAO.getQueues());

        return createdToken;
    }

    @Override
    public Token deleteToken(Long id) {
        return tokenDAO.deleteToken(id);
    }

    @Override
    public Token getTokenById(Long id) {
        return tokenDAO.getToken(id);
    }

    @Override
    public PriorityType getTokenPriorityType(Long id) {
        return tokenDAO.getToken(id).getPriority();
    }

    @Override
    public List<Token> getUnassignedTokens() {
        PremiumCustomer premiumCustomer = PremiumCustomer.getPremiumCustomer();
        RegularCustomer regularCustomer = RegularCustomer.getRegularCustomer();
        List<Token> tokens = new ArrayList<>();
        for (int i = 0; i < premiumCustomer.size(); i++) {
            tokens.add((Token) premiumCustomer.get(i));
        }
        for (int i = 0; i < regularCustomer.size(); i++) {
            tokens.add((Token) regularCustomer.get(i));
        }
        return tokens;
    }

    @Override
    public Map<Long, Token> getAllTokens() {
        return TokenStub.getAllTokens();
    }
}
