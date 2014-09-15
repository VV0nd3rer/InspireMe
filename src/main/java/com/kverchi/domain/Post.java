package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.bind.annotation.ModelAttribute;

@Entity
@Table(name="posts")
public class Post {
	private int postId, sightId;
	private String title, text, description;
	
	@Id
	@Column(name="post_id")
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	@Column(name="sight_id")
	public int getSightId() {
		return sightId;
	}
	public void setSightId(int sightId) {
		this.sightId = sightId;
	}
	@NotNull
	@Size(min = 5)
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull
	@Size(min = 5)
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotNull
	@Size(min = 10)
	@Column(name="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
