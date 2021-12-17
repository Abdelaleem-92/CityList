package com.kuehneNagel.cityList.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuehneNagel.cityList.models.City;
import com.kuehneNagel.cityList.payload.CityPayload;
import com.kuehneNagel.cityList.service.ICityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cityList")
public class CityController {

	@Autowired
	ICityService cityService;

	@GetMapping("/allCities")
	public ResponseEntity<List<City>> allCities() {
		return cityService.getAllCities();
	}

	@GetMapping("/city/{name}")
	public ResponseEntity<City> getCityByName(@NotNull @PathVariable String name) {
		return cityService.getCityByName(name);
	}

	@PostMapping("/editCity/{name}")
	@PreAuthorize("hasRole('ROLE_ALLOW_EDIT')")
	public ResponseEntity<City> editCity(@NonNull @PathVariable String name,
			@Valid @NotNull @RequestBody CityPayload cityPayload) {
		return cityService.editCityByName(name, cityPayload);
	}
}
