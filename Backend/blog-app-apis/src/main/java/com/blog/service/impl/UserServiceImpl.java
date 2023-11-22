package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.UserDto;
import com.blog.entities.UserEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repoInterfaces.UserRepo;
import com.blog.responseModel.UserResponse;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserResponse createUser(UserDto userDto) {
		UserEntity user = this.convertModelToEntity(userDto);
		UserEntity savedUser = this.userRepo.save(user);
		UserResponse dto = this.entityToDto(savedUser);
		logger.info("Entity saved with id : {}", savedUser.getId());
		return dto;
	}

	@Override
	public UserResponse updateUser(Integer userId,UserDto userDto) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", Long.valueOf(userId)));
		userEntity.setAbout(userDto.getAbout());
		userEntity.setEmailId(userDto.getEmailId());
		userEntity.setName(userDto.getName());
		userEntity.setPassword(userDto.getPassword());
		userRepo.save(userEntity);
		UserResponse dto = this.entityToDto(userEntity);
		return dto;
	}

	@Override
	public UserResponse getUserById(Integer userId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", Long.valueOf(userId)));
		UserResponse userDto = this.entityToDto(user);
		return userDto;
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<UserEntity> users = userRepo.findAll();
		List<UserResponse> userDtos = users.stream().map(user->this.entityToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", Long.valueOf(userId)));
		userRepo.delete(user);
	}

	private UserEntity convertModelToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		return userEntity;
	}

	private UserResponse entityToDto(UserEntity user) {
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}

}
