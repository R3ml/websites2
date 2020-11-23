package com.kea.websites2.controller;

import com.kea.websites2.model.Customer;
import com.kea.websites2.model.Order;
import com.kea.websites2.repository.OrderRepo;
import com.kea.websites2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    //Get all orders
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    //Get order by id
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") int id) {
        return orderService.getOrderById(id);
    }

    //Delete an order
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") int id) {
        return orderService.deleteOrder(id);
    }
}
