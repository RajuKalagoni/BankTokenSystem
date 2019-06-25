package com.bank.abc;

import com.bank.abc.dao.TokenDAO;
import com.bank.abc.model.Counters;
import com.bank.abc.model.Token;
import com.bank.abc.service.TokenServiceImpl;
import com.bank.abc.util.PriorityType;
import com.bank.abc.util.Status;
import com.bank.abc.util.TokenStub;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TokensControllerIntegrationTest {

    @InjectMocks
    TokenServiceImpl tokenService = new TokenServiceImpl();

    @Mock
    TokenDAO tokenDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateToken() {
        Map<Long, Token> tokens = TokenStub.getTokens();
        List<Token> unAssignedTokensBeforeUpdate = tokenService.getUnassignedTokens();
        Token token = new Token(1l, PriorityType.PREMIUM, Status.CANCELLED, 1l);
        token.setCounterAssigned("c1");

        Counters c1 = new Counters("c1", "Deposits");
        Counters c2 = new Counters("c2", "Drafts");
        Counters c3 = new Counters("c3", "Loans");
        List<Counters> counters = new ArrayList<>();
        counters.add(c1);
        counters.add(c2);
        counters.add(c3);

        when(tokenDAO.getQueues()).thenReturn(counters);

        tokenService.updateToken(1l, token);

        List<Token> unAssignedTokensAfterUpdate = tokenService.getUnassignedTokens();

        Assert.assertThat(unAssignedTokensBeforeUpdate.size(), Is.is(Matchers.greaterThan(unAssignedTokensAfterUpdate.size())));
    }
}
