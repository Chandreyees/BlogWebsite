package com.blog.controller;

import java.util.List;
import java.util.Map;

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

import com.blog.dtos.UserDto;
import com.blog.responseModel.ApiResponseModel;
import com.blog.responseModel.UserResponse;
import com.blog.service.UserService;

@RestController
@RequestMapping("/blog/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userReq){
		return ResponseEntity.ok(userService.createUser(userReq));		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserResponse> updateUserDetails(@PathVariable String id,@RequestBody UserDto userDto){
		return ResponseEntity.ok(userService.updateUser(Integer.parseInt(id),userDto));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserDetails(@PathVariable String id){
		return ResponseEntity.ok(userService.getUserById(Integer.parseInt(id)));		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResponse>> getAllUserDetails(){
		return ResponseEntity.ok(userService.getAllUsers());		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseModel> deleteUserDetails(@PathVariable String id){
		try {
			userService.deleteUser(Integer.parseInt(id));
		}  catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new ApiResponseModel("Invalid id", false),HttpStatus.CONFLICT);
		}
		return new ResponseEntity(new ApiResponseModel("Deleted successfully", true),HttpStatus.OK);		
	}

}
