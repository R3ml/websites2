package com.kea.websites2.service;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Customer;
import com.kea.websites2.model.Order;
import com.kea.websites2.model.Product;
import com.kea.websites2.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
     @Autowired
    OrderRepo orderRepo;

     //Get all orders
     public ResponseEntity<List<Order>> getAllOrders()
     {
         List<Order> orderList = orderRepo.findAll();
         if(orderList.isEmpty()) {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         return new ResponseEntity<>(orderList,HttpStatus.OK);
     }

     //Get order by id
    public ResponseEntity<Order> getOrderById(int id) {
         Order order = orderRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order","id",id));

         return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //Create
    //We'll see later when we have the front end how it actually works
    public ResponseEntity<Order> createOrder(Order order) {
        return new ResponseEntity<>(orderRepo.save(order), HttpStatus.CREATED);
    }

    //Update an order
    //Same here

    //Delete an order
    public ResponseEntity<HttpStatus> deleteOrder(int id) {
         orderRepo.deleteById(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
