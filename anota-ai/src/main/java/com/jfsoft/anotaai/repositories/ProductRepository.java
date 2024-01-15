package com.jfsoft.anotaai.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jfsoft.anotaai.domain.product.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
