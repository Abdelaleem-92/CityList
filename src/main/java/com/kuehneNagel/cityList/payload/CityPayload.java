package com.kuehneNagel.cityList.payload;

import javax.validation.constraints.NotBlank;

public class CityPayload {

	@NotBlank
	private String name;
	@NotBlank
	private String photoUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}
