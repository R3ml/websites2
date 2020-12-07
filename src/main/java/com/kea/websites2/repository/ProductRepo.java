package com.kea.websites2.repository;

import com.kea.websites2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findByNameContaining(String name);
}
