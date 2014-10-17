package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users_data")
public class UserData {
	
		private int userId;
	 	private String firstName;
	    private String lastName;
	    private String avatarUrl;
	    private String about;
	    private String country;
	    
	    @Id
		@Column(name = "user_id")
	    public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		@Column(name="first_name")
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		@Column(name="last_name")
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		@Column(name="avatar_url")
		public String getAvatarUrl() {
			return avatarUrl;
		}
		public void setAvatarUrl(String avatarUrl) {
			this.avatarUrl = avatarUrl;
		}
		@Column(name="about")
		public String getAbout() {
			return about;
		}
		public void setAbout(String about) {
			this.about = about;
		}
		@Column(name="country_code")
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		
		
}
