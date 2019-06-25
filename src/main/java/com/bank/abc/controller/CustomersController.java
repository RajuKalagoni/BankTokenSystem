package com.bank.abc.controller;

import com.bank.abc.model.Customer;
import com.bank.abc.service.CustomerService;
import com.bank.abc.util.PriorityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xyz")
public class CustomersController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity allCustomers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity customerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (ObjectUtils.isEmpty(customer)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Customer found with an id : " + id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/customer/{id}/premiumType", method = RequestMethod.GET)
    public ResponseEntity getCustomerType(@PathVariable Long id) {
        try {
            PriorityType priorityType = customerService.getCustomerServiceTypeById(id);
            if (ObjectUtils.isEmpty(priorityType))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Customer found with an id : " + id);

            return ResponseEntity.status(HttpStatus.OK).body(priorityType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Customer customer) {
        try {
            if(customer.getCustomerId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kindly use a unique & non-null customerId to create customer successfully..!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public ResponseEntity modifyExistingCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            if (ObjectUtils.isEmpty(updatedCustomer))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't able to update, CustomerId : "
                        + customer.getCustomerId() + " and Path variable : " + id + " must be same & not null");

            return ResponseEntity.status(HttpStatus.OK).body(updatedCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            Customer customer = customerService.deleteCustomer(id);
            if (ObjectUtils.isEmpty(customer)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Customer found with id : " + id);
            }

            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
        }
    }
}
