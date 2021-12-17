package com.kuehneNagel.cityList.security.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kuehneNagel.cityList.models.City;
import com.kuehneNagel.cityList.repository.CityRepository;
import com.kuehneNagel.cityList.security.fixtures.DataFixtures;
import com.kuehneNagel.cityList.service.impl.CityServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CityServiceTests {

	@Mock
	private CityRepository cityRepository;

	@InjectMocks
	private CityServiceImpl cityService;

	@Test
	void Validate_GetAllCities_HappyScenario() {
		Assertions.assertDoesNotThrow(() -> cityService.getAllCities());
	}

	@Test
	void validate_GetCityByName_HappyScenario() {
		String cityName = "Berlin";
		when(cityRepository.findByName(Mockito.anyString()))
				.thenReturn(Optional.of(DataFixtures.getCityByName(cityName)));
		ResponseEntity<City> cityResponse = cityService.getCityByName(cityName);
		Assertions.assertNotNull(cityResponse);
		Assertions.assertNotNull(cityResponse.getBody());
		Assertions.assertEquals(HttpStatus.OK, cityResponse.getStatusCode());
		Assertions.assertEquals(cityName, cityResponse.getBody().getName());
	}
	
	@Test
	void validate_GetCityByName_NotFoundCity() {
		String cityName = "Berlin";
		when(cityRepository.findByName(Mockito.anyString()))
				.thenReturn(Optional.empty());
		ResponseEntity<City> cityResponse = cityService.getCityByName(cityName);
		Assertions.assertNotNull(cityResponse);
		Assertions.assertNull(cityResponse.getBody());
		Assertions.assertTrue(cityResponse.getStatusCode().is4xxClientError());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, cityResponse.getStatusCode());
	}
	
	@Test
	void validate_EditCityByName_HappyScenario() {
		String cityName = "Berlin";
		String newCityName = "Berlin'";
		when(cityRepository.findByName(Mockito.anyString()))
				.thenReturn(Optional.of(DataFixtures.getCityByName(cityName)));
		when(cityRepository.save(Mockito.any()))
		.thenReturn(DataFixtures.getCityByName(newCityName));
		ResponseEntity<City> cityResponse = cityService.editCityByName(cityName, DataFixtures.getCityPayload(newCityName, "new_photo_url"));
		Assertions.assertNotNull(cityResponse);
		Assertions.assertNotNull(cityResponse.getBody());
		Assertions.assertEquals(HttpStatus.OK, cityResponse.getStatusCode());
		Assertions.assertEquals(newCityName, cityResponse.getBody().getName());
	}
	
	@Test
	void validate_EditCityByName_NotFoundCity() {
		String cityName = "Berlin";
		String newCityName = "Berlin'";
		when(cityRepository.findByName(Mockito.anyString()))
				.thenReturn(Optional.empty());
		ResponseEntity<City> cityResponse = cityService.editCityByName(cityName, DataFixtures.getCityPayload(newCityName, "new_photo_url"));
		Assertions.assertNotNull(cityResponse);
		Assertions.assertNull(cityResponse.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, cityResponse.getStatusCode());
	}

}
