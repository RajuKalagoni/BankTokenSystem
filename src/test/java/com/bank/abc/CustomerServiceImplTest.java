package com.bank.abc;

import com.bank.abc.dao.CustomerDAOImpl;
import com.bank.abc.model.Customer;
import com.bank.abc.service.CustomerServiceImpl;
import com.bank.abc.util.PriorityType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    CustomerDAOImpl customerDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomer() {
        Customer customer = new Customer(1l, "Lee", "Australia", PriorityType.PREMIUM, "B1");
        when(customerDAO.getCustomer(1l)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.getCustomerById(1l))).getCustomerId(), 1l);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(6l, "Sachin", "India", PriorityType.PREMIUM, "B1");
        when(customerDAO.createCustomer(customer)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.createCustomer(customer))).getCustomerId(), 6l);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = new Customer(7l, "Dhoni", "India", PriorityType.PREMIUM, "B1");
        when(customerDAO.updateCustomer(7l, customer)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.updateCustomer(7l, customer))).getCustomerId(), 7l);
    }

    @Test
    public void testGetTokenPriority() {
        Customer customer = new Customer(1l, "Virat", "India", PriorityType.PREMIUM, "B1");
        when(customerDAO.deleteCustomer(1l)).thenReturn(customer);
        Assert.assertEquals((long) ((customerService.deleteCustomer(1l))).getCustomerId(), 1l);
    }


}
