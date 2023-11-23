package com.blog.service;

import java.util.List;

import com.blog.dtos.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);
	void deleteCategory(Integer categoryId);
	CategoryDto getCategoryDto(Integer categoryId);
	List<CategoryDto> getAllCategories();

}
