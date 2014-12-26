package com.kverchi.service.impl;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.controller.ContentController;
import com.kverchi.dao.CountrySightDAO;
import com.kverchi.domain.CountrySight;
import com.kverchi.service.ImageService;
import com.kverchi.service.SightService;

@Repository
public class SightServiceImpl extends ContentController implements SightService {
	
	
	@Autowired 
	private CountrySightDAO sightDAO;
	@Autowired 
	private ImageService imageService;

	public List<CountrySight> getAllSights(String code, int userId) {
		return sightDAO.getSightsListByCode(code, userId);
	}

	public boolean addSight(CountrySight sight, MultipartFile img) {
		if(!img.isEmpty()){
			Set<String> allowedImageExtensions = new HashSet<String>();
			allowedImageExtensions.add("png");
			allowedImageExtensions.add("jpg");
			allowedImageExtensions.add("JPG");
			allowedImageExtensions.add("jpeg");
			allowedImageExtensions.add("JPEG");
			allowedImageExtensions.add("gif");
			imageService.setImgSizeToDefault();
			if(imageService.saveImg(img, SIGHT_IMG_PATH, allowedImageExtensions))
				//sight.setImg_url(img.getOriginalFilename());
				sightDAO.create(sight);
				return true;
			}
		return false;
	}

	public void removeSight(CountrySight sight) {
		sightDAO.delete(sight);
	}

	public CountrySight getSight(int sightId) {
		return sightDAO.getById(sightId);
	}
	
	
}
