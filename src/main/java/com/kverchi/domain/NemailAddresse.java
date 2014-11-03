package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "n_emails_addresses")
public class NemailAddresse {
	int email_id;
	String email, password;
	
	@Id
	@Column(name="email_id")
	public int getEmail_id() {
		return email_id;
	}
	public void setEmail_id(int email_id) {
		this.email_id = email_id;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
