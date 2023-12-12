package com.spring.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.sales.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
