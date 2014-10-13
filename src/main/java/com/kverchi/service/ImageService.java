package com.kverchi.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Set;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.controller.AddSightForm;
import com.kverchi.tools.Pair;


public interface ImageService {
	
	boolean saveImg(MultipartFile imgFile, String path, Set<String> allowedImageExtensions);
	BufferedImage resizeImg(Image originalImg, boolean alpha );
	void setImgSizeToDefault();
	void setCustomImgSize(int width, int height);
	
}
