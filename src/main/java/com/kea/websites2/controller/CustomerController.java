package com.kea.websites2.controller;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Customer;
import com.kea.websites2.repository.CustomerRepo;
import com.kea.websites2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

   //Get all customers
   @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
       customerService.getAllCustomers();
    }

    //Get a customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        customerService.getCustomerById(id);
    }

    //Create a customer
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    //Update a customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
    }

    //Delete a customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
       customerService.deleteCustomer(id);
    }
    
}
