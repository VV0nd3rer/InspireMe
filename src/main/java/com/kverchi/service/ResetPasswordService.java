package com.kverchi.service;

public interface ResetPasswordService {
   public String generateToken();
   public void setToken(int userId, String token);
   public int getUserIdByToken(String token);
   public void deleteToken(String token);
}
