package com.kea.websites2.controller;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Product;
import com.kea.websites2.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepo productRepo;

   //Get all products
   @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String searchTerm)
    {
        List<Product> productList;
        if(searchTerm == null) {
            productList = productRepo.findAll();
        } else {
            productList = productRepo.findByNameContaining(searchTerm);
        }


        if(productList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    //Get a product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //Create a product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepo.save(product), HttpStatus.CREATED);
    }

    //Update a product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Product _product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        _product.setName(product.getName());
        _product.setPrice(product.getPrice());
        _product.setType(product.getType());
        _product.setDescription(product.getDescription());
        _product.setImgUrl(product.getImgUrl());

        return new ResponseEntity<>(productRepo.save(_product), HttpStatus.OK);
    }

    //Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
       productRepo.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
