package com.spring.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sales.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
