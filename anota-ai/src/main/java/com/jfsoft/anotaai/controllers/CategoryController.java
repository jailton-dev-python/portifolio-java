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
import com.jfsoft.anotaai.services.CategoryService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
	
	private CategoryService service;
	
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData) {
		Category newCategory = this.service.insert(categoryData);
		return ResponseEntity.ok().body(newCategory);		
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		List<Category> categories = this.service.getAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData) {
		Category updatedCategory = this.service.update(id, categoryData);
		return ResponseEntity.ok().body(updatedCategory);		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Category> delete(@PathVariable("id") String id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();		
	}
}
