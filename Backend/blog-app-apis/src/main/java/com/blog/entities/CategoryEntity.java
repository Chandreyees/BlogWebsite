package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Category")
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(name = "Category_Title", nullable = false)
	private String categoryTitle;
	@Column(name = "Description")
	private String description;
	
	//one category might have multiple posts
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<PostEntity> posts = new ArrayList<>();
}
