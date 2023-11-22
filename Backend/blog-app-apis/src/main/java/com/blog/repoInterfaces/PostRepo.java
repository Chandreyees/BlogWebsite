package com.blog.repoInterfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.CategoryEntity;
import com.blog.entities.PostEntity;
import com.blog.entities.UserEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {

	List<PostEntity> findByUser(UserEntity user);
	List<PostEntity> findByCategory(CategoryEntity category);

}
