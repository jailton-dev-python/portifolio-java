package com.jfsoft.anotaai.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jfsoft.anotaai.domain.category.Category;
import com.jfsoft.anotaai.domain.category.dtos.CategoryDTO;
import com.jfsoft.anotaai.domain.category.dtos.ProductDTO;
import com.jfsoft.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.jfsoft.anotaai.domain.product.Product;
import com.jfsoft.anotaai.domain.product.exceptions.ProductNotFoundException;
import com.jfsoft.anotaai.repositories.CategoryRepository;
import com.jfsoft.anotaai.repositories.ProductRepository;
import com.jfsoft.anotaai.services.aws.AwsSnsService;
import com.jfsoft.anotaai.services.aws.MessageDTO;

@Service
public class ProductService {
	
	private CategoryService categoryService;
	private ProductRepository repository;
	private final AwsSnsService snsService;
	
	public ProductService(CategoryService categoryService, ProductRepository productRepository, AwsSnsService snsService) {
		this.categoryService = categoryService;
		this.repository = productRepository;
		this.snsService = snsService;
	}
	
	public Product insert(ProductDTO productData) {
		Category category = this.categoryService.getById(productData.categoryId())
				.orElseThrow(CategoryNotFoundException::new);
		Product newProduct = new Product(productData);
		newProduct.setCategory(category);
		this.repository.save(newProduct);
		this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));
		return newProduct;
	}
	
	public Product update(String id, ProductDTO productData) {
		Product product = this.repository.findById(id).
				orElseThrow(ProductNotFoundException::new);
		
		if (productData.categoryId() != null) {
			this.categoryService.getById(productData.categoryId())
				.ifPresent(product::setCategory);			
		}		
		
		if (!productData.title().isEmpty()) {
			product.setTitle(productData.title());
		}
		
		if (!productData.description().isEmpty()) {
			product.setDescription(productData.description());
		}
		
		if (!(productData.price() == null)) {
			product.setPrice(productData.price());
		}
		
		this.repository.save(product);
		this.snsService.publish(new MessageDTO(product.getOwnerId()));
		
		return product;
	}
	
	public void delete(String id) {
		Product product = this.repository.findById(id).
				orElseThrow(ProductNotFoundException::new);
		
		this.repository.delete(product);
	}
	
	public List<Product> getAll() {
		return this.repository.findAll();
	}
}
