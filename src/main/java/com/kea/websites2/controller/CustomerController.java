package com.kea.websites2.controller;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Customer;
import com.kea.websites2.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepo customerRepo;

   //Get all customers
   @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        List<Customer> customerList = customerRepo.findAll();
        if(customerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    //Get a customer by id
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        Customer customer = customerRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //Create a customer
    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
    }

    //Update a customer
    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        Customer _customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setOrdersById(customer.getOrdersById());

        return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.OK);
    }

    //Delete a customer
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id) {
       customerRepo.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
