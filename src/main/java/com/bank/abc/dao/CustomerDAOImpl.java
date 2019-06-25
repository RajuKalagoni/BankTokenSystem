package com.bank.abc.dao;

import com.bank.abc.model.Customer;
import com.bank.abc.util.CustomerStub;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    public List<Customer> allCustomers() {
        return CustomerStub.getAllCustomers();
    }

    public Customer createCustomer(Customer customer) {
        return CustomerStub.create(customer);
    }

    public Customer getCustomer(Long id) {
        return CustomerStub.getCustomerById(id);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        return CustomerStub.updateCustomer(id, customer);
    }

    public Customer deleteCustomer(Long id) {
        return CustomerStub.deleteCustomer(id);
    }
}
