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

import com.blog.dtos.PostDto;
import com.blog.responseModel.ApiResponseModel;
import com.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("blog/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto req, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		return ResponseEntity.ok(postService.createPost(req, userId, categoryId));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable String id,@Valid @RequestBody PostDto req){
		return ResponseEntity.ok(postService.updatePost(req, Integer.parseInt(id)));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable String id){
		return ResponseEntity.ok(postService.getPostById(Integer.parseInt(id)));		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		return ResponseEntity.ok(postService.getAllPost());		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseModel> deleteCategorie(@PathVariable String id){
		try {
			postService.deletePost(Integer.parseInt(id));
		}  catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ApiResponseModel("Invalid id", false),HttpStatus.CONFLICT);
		}
		return new ResponseEntity(new ApiResponseModel("Deleted successfully", true),HttpStatus.OK);		
	}


	@GetMapping("/all/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(postService.getPostByCategory(categoryId));
	}
	
	@GetMapping("/all/user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId){
		return ResponseEntity.ok(postService.getPostByUser(userId));
	}
}
