package com.kea.websites2.service;

import com.kea.websites2.exception.ResourceNotFoundException;
import com.kea.websites2.model.Product;
import com.kea.websites2.model.utils.PagingHeaders;
import com.kea.websites2.model.utils.PagingResponse;
import com.kea.websites2.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public PagingResponse getAllProducts(Specification<Product> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return getAllProducts(spec, buildPageRequest(headers, sort));
        } else {
            List<Product> entities = getAllProducts(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    private boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

    public PagingResponse getAllProducts(Specification<Product> spec, Pageable pageable) {
        Page<Product> page = repo.findAll(spec, pageable);
        List<Product> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    public List<Product> getAllProducts(Specification<Product> spec, Sort sort) {
        return repo.findAll(spec, sort);
    }

    //Get a product by id
    public ResponseEntity<Product> getProductById(int id) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //Create a product
    public ResponseEntity<Product> createProduct(Product product) {
        return new ResponseEntity<>(repo.save(product), HttpStatus.CREATED);
    }

    //Update a product
    public ResponseEntity<Product> updateProduct(int id, Product product) {
        Product _product = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        _product.setName(product.getName());
        _product.setPrice(product.getPrice());
        _product.setType(product.getType());
        _product.setDescription(product.getDescription());
        _product.setImgUrl(product.getImgUrl());

        return new ResponseEntity<>(productRepo.save(_product), HttpStatus.OK);
    }

    //Delete a product
    public ResponseEntity<HttpStatus> deleteProduct(int id) {
        repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
