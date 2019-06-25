package com.bank.abc.service;

import com.bank.abc.dao.CustomerDAO;
import com.bank.abc.model.Customer;
import com.bank.abc.util.PriorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.allCustomers();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerDAO.updateCustomer(id, customer);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    public PriorityType getCustomerServiceTypeById(Long id) {
        if (customerDAO.getCustomer(id) != null)
            return customerDAO.getCustomer(id).getCustomerType();
        return null;
    }
}
