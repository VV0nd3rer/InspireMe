package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="posts")
public class Post {
	private int postId, sightId;
	private String title, text, description, username;
	private Date stampCreated, stampUpdated;
	@Id
	@NotNull
	@Column(name="post_id")
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	@NotNull
	@Column(name="sight_id")
	public int getSightId() {
		return sightId;
	}
	public void setSightId(int sightId) {
		this.sightId = sightId;
	}
	@Column(name="stamp_created")
	public Date getStampCreated() {
		return stampCreated;
	}
	public void setStampCreated(Date stampCreated) {
		this.stampCreated = stampCreated;
	}
	@Column(name="stamp_updated")
	public Date getStampUpdated() {
		return stampUpdated;
	}
	public void setStampUpdated(Date stampUpdated) {
		this.stampUpdated = stampUpdated;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotNull
	@Size(min = 5, max = 250)
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull
	@Size(min = 5, max = 45)
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
