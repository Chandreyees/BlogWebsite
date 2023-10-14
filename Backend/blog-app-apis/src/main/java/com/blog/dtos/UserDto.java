package com.blog.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {

	private Integer id;
	private String name;
	private String emailId;
	private String password;
	private String about;
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
}
