package com.spring.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sales.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
