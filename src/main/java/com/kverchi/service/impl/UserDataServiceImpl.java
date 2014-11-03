package com.kverchi.service.impl;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.dao.UserDataDAO;
import com.kverchi.domain.UserData;
import com.kverchi.service.ImageService;
import com.kverchi.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired private UserDataDAO userDataDAO;
	@Autowired private ImageService imageService;
	private final static String IMG_PATH="E:/Java/sts-3.6.1.RELEASE/workspace/InspireMe/src/main/webapp/countryImg/countries_sights";
	
	@Override
	public UserData getUserData(int userId) {
		UserData ud = userDataDAO.getById(userId);
		return ud;
	}

	@Override
	public void createUserData(UserData data) {
		userDataDAO.create(data);

	}

	@Override
	public void deleteUserData(int userId) {
		userDataDAO.delete(userDataDAO.getById(userId));

	}

	@Override
	public void updateUserData(UserData data, MultipartFile avatarImg) {
		if(!avatarImg.isEmpty()){
		Set<String> allowedImageExtensions = new HashSet<String>();
		allowedImageExtensions.add("png");
		allowedImageExtensions.add("jpg");
		allowedImageExtensions.add("JPG");
		allowedImageExtensions.add("jpeg");
		allowedImageExtensions.add("JPEG");
		allowedImageExtensions.add("gif");
		
		imageService.setCustomImgSize(150, 100);
		imageService.saveImg(avatarImg, IMG_PATH, allowedImageExtensions);
		if(!data.getAvatarUrl().equals("noavatar.jpg"))
		{
		File delFile = new File(IMG_PATH+data.getAvatarUrl());
		delFile.delete();
		}
		data.setAvatarUrl(avatarImg.getOriginalFilename());
		}
		userDataDAO.update(data);

	}

}
