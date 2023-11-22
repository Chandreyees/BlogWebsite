package com.blog.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {

	private Integer id;
	@NotEmpty(message = "User Name cannot be blank")
	private String name;
	@Email(message = "Email Id is invalid")
	private String emailId;
	@NotBlank
	@Size(min = 3, max = 20, message="Password must be of min 3 chars or max 10 chars")
	private String password;
	@NotEmpty
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
