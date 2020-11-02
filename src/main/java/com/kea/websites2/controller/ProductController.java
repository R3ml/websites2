package com.kea.websites2.controller;

import com.kea.websites2.model.Product;
import com.kea.websites2.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepo productRepo;

   /* @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {

    }*/
}
