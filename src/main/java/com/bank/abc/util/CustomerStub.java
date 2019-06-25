package com.bank.abc.util;

import com.bank.abc.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerStub {
    private static Map<Long, Customer> customers = new HashMap<>();
    private static long idIndex;

    static {
        customers.put(idIndex, new Customer(idIndex, "Raju", "Hyd", PriorityType.PREMIUM, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Jana", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Emily", "Hyd", PriorityType.PREMIUM, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Scott", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Mahesh", "Hyd", PriorityType.PREMIUM, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Chandra", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Mohan", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Lucky", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Peter", "Hyd", PriorityType.REGULAR, "B1"));
        customers.put(++idIndex, new Customer(idIndex, "Julie", "Hyd", PriorityType.PREMIUM, "B1"));
    }

    public static List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public static Customer create(Customer customer) {
        /*customer.setCustId(++idIndex);
        customers.put(idIndex, customer);*/
        if(customer.getCustomerId() != null) {
            boolean isExistingCustomerId = customers.containsKey(customer.getCustomerId());
            if (!isExistingCustomerId) {
                customers.put(++idIndex, new Customer(customer));
                return customer;
            }
            return customers.get(customer.getCustomerId());
        }
        return null;
    }

    public static Customer getCustomerById(Long id) {
        if(id != null && id >= 0) {
            return customers.get(id);
        }
        else return null;
    }

    public static Customer updateCustomer(Long id, Customer customer) {
        if(id != null && id == customer.getCustomerId()) {
            customers.put(id, customer);
            return customer;
        }
        return null;
    }

    public static Customer deleteCustomer(Long id) {
        return customers.remove(id);
    }
}
