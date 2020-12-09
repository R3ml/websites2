package com.kea.websites2.controller;

import com.kea.websites2.model.Customer;
import com.kea.websites2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:8081",
        "https://websites2-frontend.herokuapp.com/"
})
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

   //Get all customers
   @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
       return customerService.getAllCustomers();
    }

    //Get a customer by id
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        return customerService.getCustomerById(id);
    }

    //Create a customer
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    //Update a customer
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
       return customerService.updateCustomer(id, customer);
    }

    //Delete a customer
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
       return customerService.deleteCustomer(id);
    }
    
}
