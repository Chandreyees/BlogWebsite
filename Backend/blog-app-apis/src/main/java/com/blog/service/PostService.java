package com.blog.service;

import java.util.List;

import com.blog.dtos.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postReq);
	PostDto updatePost(PostDto req, Integer postId);
	void deletePost(Integer id);
	List<PostDto> getAllPost();
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);

}
