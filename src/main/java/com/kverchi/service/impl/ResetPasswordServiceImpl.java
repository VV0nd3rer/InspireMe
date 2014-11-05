package com.kverchi.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.ResetPasswordDAO;
import com.kverchi.domain.ResetPassword;
import com.kverchi.service.ResetPasswordService;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
	private static final int TOKEN_EXPIRE_TIME = 24*3600*1000;
	@Autowired
	private ResetPasswordDAO resetPasswordDAO;
	@Override
	public String generateToken() {
		String token = new String();
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			token = new Integer(sr.nextInt()).toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error in ResetPasswordService->generateToken: " + e);
		}
		return token;
	}
	@Override
	public void setToken(int userId, String token) {
		ResetPassword resPass = new ResetPassword();
		resPass.setUserId(userId);
		resPass.setToken(token);
		Date dt = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		resPass.setTimeCreated(currentTime);
		resetPasswordDAO.create(resPass);
	}

	@Override
	public int getUserIdByToken(String token) {
		ResetPassword resPass = null;
		int res = 0;
		resPass = resetPasswordDAO.getById(token);
		if (resPass == null)
			return res;
		Date currDate = new Date();
		Date createdDate = null;
		String sCreatedD = resPass.getTimeCreated();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			createdDate = formatter.parse(sCreatedD);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long resDate = currDate.getTime() - createdDate.getTime();
		if (resDate > TOKEN_EXPIRE_TIME) {
			System.out.println("End time is greater than start time");
			return res;
		}
		return resPass.getUserId();
	}
	@Override
	public void deleteToken(String token) {
		resetPasswordDAO.delete(resetPasswordDAO.getById(token));
	}
	
}
