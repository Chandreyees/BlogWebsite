package com.blog.entities;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class UserEntity {
	
	@Id
	private Integer id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "ABOUT")
	private String about;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<PostEntity> posts = new ArrayList<>();
	
	public UserEntity() {
		super();
	}
	public UserEntity(Integer id, String name, String emailId, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.about = about;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", password=" + password + ", about="
				+ about + "]";
	}
	
	

}
