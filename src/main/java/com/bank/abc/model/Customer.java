package com.bank.abc.model;

import com.bank.abc.util.PriorityType;

import java.util.Objects;

public class Customer {
    private Long customerId;
    private String customerName;
    private String customerAddress;
    private PriorityType customerType;
    private String customerBranchId;

    public Customer(Customer customer){
        this.customerId = customer.customerId;
        this.customerName = customer.customerName;
        this.customerAddress = customer.customerAddress;
        this.customerType = customer.customerType;
        this.customerBranchId = customer.customerBranchId;
    }

    public Customer(Long customerId, String customerName, String customerAddress, PriorityType customerType, String customerBranchId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerType = customerType;
        this.customerBranchId = customerBranchId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public PriorityType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(PriorityType customerType) {
        this.customerType = customerType;
    }

    public String getCustomerBranchId() {
        return customerBranchId;
    }

    public void setCustomerBranchId(String customerBranchId) {
        this.customerBranchId = customerBranchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
