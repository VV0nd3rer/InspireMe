package com.kverchi.controller;

import javax.validation.constraints.NotNull;

public class AddSightForm {
	private String title;
	private String description;
	private String img_url;
	
	@NotNull
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
}
