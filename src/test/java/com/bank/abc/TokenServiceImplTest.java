package com.bank.abc;

import com.bank.abc.dao.TokenDAOImpl;
import com.bank.abc.model.Counters;
import com.bank.abc.model.Token;
import com.bank.abc.service.TokenServiceImpl;
import com.bank.abc.util.PriorityType;
import com.bank.abc.util.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class TokenServiceImplTest {

    @InjectMocks
    TokenServiceImpl tokenService;

    @Mock
    TokenDAOImpl tokenDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetToken() {
        Token token = new Token(1L, PriorityType.PREMIUM, Status.OPEN, 1L);
        when(tokenService.getTokenById(1L)).thenReturn(token);
        Assert.assertEquals((long) ((Token) (tokenService.getTokenById(1L))).getId(), 1L);
    }

    @Test
    public void testCreateToken() {
        Token token = new Token(1L, PriorityType.PREMIUM, Status.OPEN, 1L);
        when(tokenDAO.createToken(token)).thenReturn(token);
        Counters c1 = new Counters("c1", "Deposits");
        Counters c2 = new Counters("c2", "Drafts");
        Counters c3 = new Counters("c3", "Loans");
        List<Counters> counters = new ArrayList<>();
        counters.add(c1);
        counters.add(c2);
        counters.add(c3);
        when(tokenDAO.getQueues()).thenReturn(counters);
        Assert.assertEquals((long) ((tokenService.createToken(token))).getId(), 1L);
    }

    @Test
    public void testUpdateToken() {
        Token token = new Token(1L, PriorityType.PREMIUM, Status.OPEN, 1L);
        when(tokenService.updateToken(1L, token)).thenReturn(token);
        Assert.assertEquals((long) (tokenService.updateToken(1L, token)).getId(), 1L);
    }

    @Test
    public void testGetTokenPriority() {
        Token token = new Token(1L, PriorityType.PREMIUM, Status.OPEN, 1L);
        when(tokenDAO.getToken(1L)).thenReturn(token);
        Assert.assertEquals((tokenService.getTokenPriorityType(1L)), PriorityType.PREMIUM);
    }
}
