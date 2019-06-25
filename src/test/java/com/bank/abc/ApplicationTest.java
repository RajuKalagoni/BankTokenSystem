package com.bank.abc;

import com.bank.abc.controller.CustomersController;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ApplicationTest {

    @Test
    public void testApp() {
        CustomersController cc = new CustomersController();
        cc.customerById(1l);
        assertNotNull(cc.customerById(1l));
    }
}
