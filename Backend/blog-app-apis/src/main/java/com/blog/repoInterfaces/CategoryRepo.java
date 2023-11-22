package com.blog.repoInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer> {

}
