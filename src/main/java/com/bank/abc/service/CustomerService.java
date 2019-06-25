package com.bank.abc.service;

import com.bank.abc.model.Customer;
import com.bank.abc.util.PriorityType;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);
    Customer createCustomer(Customer customer);
    Customer deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    PriorityType getCustomerServiceTypeById(Long id);
}
