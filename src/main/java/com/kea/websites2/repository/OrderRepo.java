package com.kea.websites2.repository;

import com.kea.websites2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepo extends JpaRepository<Order, Integer>, PagingAndSortingRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
}
