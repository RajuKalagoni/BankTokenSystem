package com.bank.abc.dao;

import com.bank.abc.model.Counters;
import com.bank.abc.model.Token;

import java.util.List;
import java.util.Map;

public interface TokenDAO {
    List<Counters> getQueues();
    Map<Long, Token> getTokens();
    Token createToken(Token token);
    Token getToken(Long id);
    Token updateToken(Long id, Token token);
    Token deleteToken(Long id);
}
