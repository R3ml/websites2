package com.kea.websites2.repository;

import com.kea.websites2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String name);
}
