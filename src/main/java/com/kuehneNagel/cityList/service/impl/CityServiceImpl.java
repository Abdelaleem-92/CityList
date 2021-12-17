package com.kuehneNagel.cityList.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kuehneNagel.cityList.models.City;
import com.kuehneNagel.cityList.payload.CityPayload;
import com.kuehneNagel.cityList.repository.CityRepository;
import com.kuehneNagel.cityList.service.ICityService;

@Service
public class CityServiceImpl implements ICityService{

	@Autowired
	CityRepository cityRepository;

	@Override
	public ResponseEntity<List<City>> getAllCities() {
		return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<City> getCityByName(String name) {
		Optional<City> cityOptional = cityRepository.findByName(name);
		if (cityOptional.isPresent()) {
			return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<City> editCityByName(String existingCityName, CityPayload cityPayload) {
		Optional<City> existingCityOptional = cityRepository.findByName(existingCityName);
		if (existingCityOptional.isPresent()) {
			updateCity(existingCityOptional.get(), cityPayload);
			return new ResponseEntity<>(existingCityOptional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private void updateCity(City existingCity, CityPayload cityPayload) {
		if(cityPayload.getName() != null) 
			existingCity.setName(cityPayload.getName());
		
		if(cityPayload.getPhotoUrl() != null) 
			existingCity.setPhotoUrl(cityPayload.getPhotoUrl());
		
		cityRepository.save(existingCity);
		
	}
	
}
