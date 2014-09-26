package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="countries_sights")
public class CountrySight {
	
		private int sight_id;
	    private String sight_label;
	    private String country_code;
	    private String img_url;
	    private String description;
	    private String userId;
	    
	    
		@Id
		@Column(name="sight_id")
		public int getSight_id() {
			return sight_id;
		}
		public void setSight_id(int sight_id) {
			this.sight_id = sight_id;
		}
		
		@Column(name="sight_label")
		public String getSight_label() {
			return sight_label;
		}
		public void setSight_label(String sight_label) {
			this.sight_label = sight_label;
		}
		
		@Column(name="country_code")
		public String getCountry_code() {
			return country_code;
		}
		public void setCountry_code(String country_code) {
			this.country_code = country_code;
		}
		
		@Column(name="img_url")
		public String getImg_url() {
			return img_url;
		}
		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		
		@Column(name="description")
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		@Column(name="userId")
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		
		
		
	    
}
