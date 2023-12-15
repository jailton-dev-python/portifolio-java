package com.spring.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sales.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
