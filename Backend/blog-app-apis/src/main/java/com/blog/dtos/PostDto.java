package com.blog.dtos;

import java.util.Date;

import com.blog.entities.CategoryEntity;
import com.blog.entities.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String title;
	private String content;
}
