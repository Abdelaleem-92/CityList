package com.kuehneNagel.cityList.security.fixtures;

import com.kuehneNagel.cityList.models.City;
import com.kuehneNagel.cityList.payload.CityPayload;
import com.kuehneNagel.cityList.payload.LoginRequest;

public class DataFixtures {

	public static LoginRequest getLoginRequest_ValidCredentials() {
		LoginRequest req = new LoginRequest();
		req.setUsername("kuehneNagel");
		req.setPassword("kuehneNagel");
		return req;
	}

	public static LoginRequest getLoginRequest_InValidCredentials() {
		LoginRequest req = new LoginRequest();
		req.setUsername("kuehneNagel");
		req.setPassword("Wrong_Value");
		return req;
	}

	public static City getCityByName(String cityName) {
		City city = new City();
		city.setId(10);
		city.setName(cityName);
		city.setPhotoUrl("dummy_Photo_Url");
		return city;
	}

	public static CityPayload getCityPayload(String cityName, String photoUrl) {
		CityPayload city = new CityPayload();
		city.setName(cityName);
		city.setPhotoUrl(photoUrl);
		return city;
	}
}
