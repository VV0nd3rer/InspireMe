package com.kverchi.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AddSightForm {
	private String title;
	private String description;
	private CommonsMultipartFile img_url;
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
	public CommonsMultipartFile  getImg_url() {
		return img_url;
	}
	public void setImg_url(CommonsMultipartFile  img_url) {
		this.img_url = img_url;
	}
	
}
