package com.blog.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Post")
@NoArgsConstructor
@Getter
@Setter
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "Post_Title")
	private String title;
	@Column(name = "Content", length = 10000)
	private String content;
	@Column(name = "Image_Name")
	private String imageName;
	private Date addedDate;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	@ManyToOne
	private UserEntity user;
	
	public PostEntity(Integer id, String title, String content, String imageName, Date addedDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
	}
	
	
}
