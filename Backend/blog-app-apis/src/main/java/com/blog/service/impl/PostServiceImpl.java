package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dtos.PostDto;
import com.blog.entities.CategoryEntity;
import com.blog.entities.PostEntity;
import com.blog.entities.UserEntity;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repoInterfaces.CategoryRepo;
import com.blog.repoInterfaces.PostRepo;
import com.blog.repoInterfaces.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	public static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private PostRepo repo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDto createPost(PostDto postReq, Integer userId, Integer categoryId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", Long.valueOf(userId)));
		CategoryEntity category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", Long.valueOf(categoryId)));
		PostEntity postEntity = new PostEntity();
		BeanUtils.copyProperties(postReq, postEntity);
		postEntity.setCategory(category);
		postEntity.setUser(user);
		postEntity.setAddedDate(new Date());
		repo.save(postEntity);
		logger.info("Entity saved with id : {}", postEntity.getId());
		return convertEntityToDto(postEntity);
	}

	@Override
	public PostDto updatePost(PostDto req, Integer postId) {
		PostEntity postEntity = repo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Long.valueOf(postId)));
		postEntity.setTitle(req.getTitle());
		postEntity.setContent(req.getContent());
		postEntity.setImageName(req.getImageUrl());
		CategoryEntity category = new CategoryEntity();
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(req.getCategory(), category);
		BeanUtils.copyProperties(req.getUser(), user);
		postEntity.setCategory(category);
		postEntity.setUser(user);
		repo.save(postEntity);
		return convertEntityToDto(postEntity);
	}

	@Override
	public void deletePost(Integer postId) {
		PostEntity post = repo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Long.valueOf(postId)));
		repo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
		List<PostEntity> postEntities = repo.findAll();
		return postEntities.stream().map(entity->convertEntityToDto(entity)).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(Integer postId) {
		PostEntity entity = repo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Long.valueOf(postId)));
		return convertEntityToDto(entity);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		CategoryEntity categoryEntity = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", Long.valueOf(categoryId)));
		List<PostEntity> posts = repo.findByCategory(categoryEntity);
		return posts.stream().map(post->convertEntityToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", Long.valueOf(userId)));
		List<PostEntity> posts = repo.findByUser(user);
		return posts.stream().map(post->convertEntityToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	private PostDto convertEntityToDto(PostEntity postEntity) {
		PostDto postResponse = new PostDto();
		BeanUtils.copyProperties(postEntity, postResponse);
		return postResponse;
	}

}
