package com.jfsoft.anotaai.domain.product;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jfsoft.anotaai.domain.category.Category;
import com.jfsoft.anotaai.domain.category.dtos.ProductDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor	
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String title;
	private String description;
	private String ownerId;
	private Integer price; // Gravar multiplica por 100 para remover casas decimais
	                       // Obter divide por 100 para obter casas decimais
	private Category category;
	
	public Product(ProductDTO data) {
		this.title = data.title();
		this.description = data.description();
		this.ownerId = data.ownerId();
		this.price = data.price();
	}
}
