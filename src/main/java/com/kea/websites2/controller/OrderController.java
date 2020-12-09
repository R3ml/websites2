package com.kea.websites2.controller;

import com.kea.websites2.model.Customer;
import com.kea.websites2.model.Order;
import com.kea.websites2.repository.OrderRepo;
import com.kea.websites2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:8081"
        "https://websites2-frontend.herokuapp.com/"
})
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
