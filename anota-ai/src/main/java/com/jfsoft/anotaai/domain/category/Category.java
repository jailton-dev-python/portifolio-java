package com.jfsoft.anotaai.domain.category;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jfsoft.anotaai.domain.category.dtos.CategoryDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String title;
	private String description;
	private String ownerId;
	
	public Category(CategoryDTO categoryDTO) {
		this.title = categoryDTO.title();
		this.description = categoryDTO.description();
		this.ownerId = categoryDTO.ownerId();
	}
}
