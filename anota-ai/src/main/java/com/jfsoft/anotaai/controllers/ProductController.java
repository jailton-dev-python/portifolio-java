package com.jfsoft.anotaai.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jfsoft.anotaai.domain.category.Category;
import com.jfsoft.anotaai.domain.category.dtos.CategoryDTO;
import com.jfsoft.anotaai.domain.category.dtos.ProductDTO;
import com.jfsoft.anotaai.domain.product.Product;
import com.jfsoft.anotaai.services.CategoryService;
import com.jfsoft.anotaai.services.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {
		Product newProduct = this.service.insert(productData);
		return ResponseEntity.ok().body(newProduct);		
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = this.service.getAll();
		return ResponseEntity.ok().body(products);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData) {
		Product updatedProduct = this.service.update(id, productData);
		return ResponseEntity.ok().body(updatedProduct);		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> delete(@PathVariable("id") String id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();		
	}
}
