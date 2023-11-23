package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.CategoryDto;
import com.blog.entities.CategoryEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repoInterfaces.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

	
	@Autowired
	private CategoryRepo repo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryEntity categoryEntity = new CategoryEntity();
		BeanUtils.copyProperties(categoryDto, categoryEntity);
		repo.save(categoryEntity);
		logger.info("Entity saved with id : {}", categoryEntity.getCategoryId());
		categoryDto.setCategoryId(categoryEntity.getCategoryId());
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		CategoryEntity categoryEntity = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", Long.valueOf(id)));
		categoryEntity.setCategoryTitle(categoryDto.getCategoryTitle());
		categoryEntity.setDescription(categoryDto.getDescription());
		CategoryEntity catUpdated = repo.save(categoryEntity);
		logger.info("Category Entity updated with id : {}", catUpdated.getCategoryId());
		categoryDto.setCategoryId(categoryEntity.getCategoryId());
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		CategoryEntity categoryEntity = repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", Long.valueOf(categoryId)));
		repo.delete(categoryEntity);
		logger.info("Category deleted with id : {}", categoryEntity.getCategoryId());
	}

	@Override
	public CategoryDto getCategoryDto(Integer categoryId) {
		CategoryEntity categoryEntity = repo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", Long.valueOf(categoryId)));
		CategoryDto categoryDto = new CategoryDto();
		BeanUtils.copyProperties(categoryEntity, categoryDto);
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryEntity> categoryEntities = repo.findAll();
		return categoryEntities.stream().map(category -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(category, dto);
			return dto;
		}).collect(Collectors.toList());
	}

}
