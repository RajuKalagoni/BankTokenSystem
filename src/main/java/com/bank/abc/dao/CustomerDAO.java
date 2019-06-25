package com.bank.abc.dao;

import com.bank.abc.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> allCustomers();
    Customer createCustomer(Customer customer);
    Customer getCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
    Customer deleteCustomer(Long id);
}