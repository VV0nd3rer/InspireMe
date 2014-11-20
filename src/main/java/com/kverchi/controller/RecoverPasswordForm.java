package com.kverchi.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RecoverPasswordForm {
 private String email, captcha;
 @NotNull
 @Email
 @Size(min = 5, max = 30)
 public String getEmail() {
	 return email;
 }
 public void setEmail(String email) {
	 this.email = email;
 }
 public String getCaptcha() {
	 return captcha;
 }
 public void setCaptcha(String captcha) {
	 this.captcha = captcha;
 } 
}
