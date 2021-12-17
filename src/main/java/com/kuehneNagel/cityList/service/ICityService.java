package com.kuehneNagel.cityList.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.kuehneNagel.cityList.models.City;
import com.kuehneNagel.cityList.payload.CityPayload;

public interface ICityService {

	public ResponseEntity<List<City>> getAllCities();

	public ResponseEntity<City> getCityByName(String name);

	public ResponseEntity<City> editCityByName(String existingCityName, CityPayload cityPayload);

}
