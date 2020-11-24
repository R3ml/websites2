package com.kea.websites2.service;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Customer;
import com.kea.websites2.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    //Get all customers
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customerList = customerRepo.findAll();
        if(customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    //Get a customer by id
    public ResponseEntity<Customer> getCustomerById(int id) {
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //Create a customer
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
    }

    //Update a customer
    public ResponseEntity<Customer> updateCustomer(int id, Customer customer) {
        Customer _customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        _customer.setFirstName(customer.getFirstName());
        _customer.setLastName(customer.getLastName());
        _customer.setEmail(customer.getEmail());

        return new ResponseEntity<>(customerRepo.save(_customer), HttpStatus.OK);
    }

    //Delete a customer
    public ResponseEntity<HttpStatus> deleteCustomer(int id) {
        customerRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
