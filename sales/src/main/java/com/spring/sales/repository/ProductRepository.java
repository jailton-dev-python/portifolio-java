package com.spring.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sales.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
