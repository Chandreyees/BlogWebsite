package com.blog.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private String title;
	private String content;
	@JsonProperty(value ="image_url")
	private String imageUrl;
	private UserDto user;
	private CategoryDto category;
	private Date addedDate;
	
}
