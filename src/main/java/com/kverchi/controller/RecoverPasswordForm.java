package com.kverchi.controller;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RecoverPasswordForm {
 private String email;
 @NotEmpty
 @Email
 public String getEmail() {
	 return email;
 }

 public void setEmail(String email) {
	 this.email = email;
 }
 
 
}
