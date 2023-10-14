package com.blog.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>{

}
