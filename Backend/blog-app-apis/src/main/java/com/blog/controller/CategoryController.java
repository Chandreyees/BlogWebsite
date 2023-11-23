package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dtos.CategoryDto;
import com.blog.responseModel.ApiResponseModel;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("blog/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryReq){
		return ResponseEntity.ok(categoryService.createCategory(categoryReq));		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable String id,@Valid @RequestBody CategoryDto categoryReq){
		return ResponseEntity.ok(categoryService.updateCategory(categoryReq, Integer.parseInt(id)));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable String id){
		return ResponseEntity.ok(categoryService.getCategoryDto(Integer.parseInt(id)));		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseModel> deleteCategorie(@PathVariable String id){
		try {
			categoryService.deleteCategory(Integer.parseInt(id));
		}  catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ApiResponseModel("Invalid id", false),HttpStatus.CONFLICT);
		}
		return new ResponseEntity(new ApiResponseModel("Deleted successfully", true),HttpStatus.OK);		
	}

}
