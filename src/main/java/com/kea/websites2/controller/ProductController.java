package com.kea.websites2.controller;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Product;
import com.kea.websites2.model.utils.PagingHeaders;
import com.kea.websites2.model.utils.PagingResponse;
import com.kea.websites2.repository.ProductRepo;
import com.kea.websites2.service.ProductService;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepo productRepo;

   //Get all products
   @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(
           @And({
                    @Spec(path = "name", params = "name", spec = Like.class),
                    @Spec(path = "price", params = "price", spec = Like.class),
                    @Spec(path = "type", params = "type", spec = Like.class),
            }) Specification<Product> spec,
           Sort sort,
           @RequestHeader HttpHeaders headers) {
        final PagingResponse response = productService.getAllProducts(spec, headers, sort);
        return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    public HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }

    //Get a product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) {
        productService.getProductById(id);
    }

    //Create a product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    //Update a product
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    //Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") int id) {
       productService.deleteProduct(id);
    }
}
