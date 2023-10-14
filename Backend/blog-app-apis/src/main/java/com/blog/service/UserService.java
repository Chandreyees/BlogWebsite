package com.blog.service;

import java.util.List;

import com.blog.dtos.UserDto;
import com.blog.responseModel.UserResponse;

public interface UserService {
	
	UserResponse createUser(UserDto userDto);
	UserResponse updateUser(Integer userId,UserDto userDto);
	UserResponse getUserById(Integer userId);
	List<UserResponse> getAllUsers();
	void deleteUser(Integer userId) throws Exception;

}
