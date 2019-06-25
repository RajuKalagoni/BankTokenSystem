package com.bank.abc.service;


import com.bank.abc.model.Token;
import com.bank.abc.util.PriorityType;

import java.util.List;
import java.util.Map;

public interface TokenService {
    Map<String, List<String>> list();
    Token updateToken(Long id, Token token);
    Token createToken(Token token);
    Token deleteToken(Long id);
    Token getTokenById(Long id);
    PriorityType getTokenPriorityType(Long id);
    List<Token> getUnassignedTokens();
    Map<Long, Token> getAllTokens();
}
