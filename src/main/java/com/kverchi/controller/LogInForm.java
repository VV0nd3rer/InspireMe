package com.kverchi.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LogInForm {
	@NotNull(message="Write your login")
	@Size(min = 1, max = 50)
	private String login;
	private String password;

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull
	@Size(min = 6, max = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
