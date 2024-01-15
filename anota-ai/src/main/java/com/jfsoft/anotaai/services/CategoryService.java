package com.jfsoft.anotaai.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jfsoft.anotaai.domain.category.Category;
import com.jfsoft.anotaai.domain.category.dtos.CategoryDTO;
import com.jfsoft.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.jfsoft.anotaai.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository repository;
	
	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}
	
	public Category insert(CategoryDTO categoryData) {
		Category newCategory = new Category(categoryData); 
		this.repository.save(newCategory);
		return newCategory;
	}
	
	public Category update(String id, CategoryDTO categoryData) {
		Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
		
		if (!categoryData.title().isEmpty()) {
			category.setTitle(categoryData.title());
		}
		
		if (!categoryData.description().isEmpty()) {
			category.setDescription(categoryData.description());
		}
		
		this.repository.save(category);
		
		return category;
	}
	
	public void delete(String id) {
		Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
		
		this.repository.delete(category);
	}
	
	public List<Category> getAll() {
		return this.repository.findAll();
	}
	
	public Optional<Category> getById(String id) {
		return this.repository.findById(id);
	}
}
