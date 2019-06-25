package com.bank.abc.dao;

import com.bank.abc.model.Counters;
import com.bank.abc.model.Token;
import com.bank.abc.util.TokenStub;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TokenDAOImpl implements TokenDAO {
    public List<Counters> getQueues() {
        return TokenStub.getQueues();
    }

    public Map<Long, Token> getTokens() {
        return TokenStub.getTokens();
    }

    public Token createToken(Token token) {
        return TokenStub.create(token);
    }

    public Token getToken(Long id) {
        return TokenStub.get(id);
    }

    public Token updateToken(Long id, Token token) {
        return TokenStub.update(id, token);
    }

    public Token deleteToken(Long id) {
        return TokenStub.delete(id);
    }
}
