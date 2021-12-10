package com.kuehneNagel.cityList.security.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuehneNagel.cityList.security.fixtures.DataFixtures;
import com.kuehneNagel.cityList.security.utils.JwtTokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CityControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	JwtTokenUtil jwtUtils;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void should_AllowAccessTo_NotSecuredApis_AllCities() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/cityList/allCities")).andExpect(status().isOk());
	}

	@Test //  no token is required
	public void should_AllowAccessTo_NotSecuredApis_GetCity() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/cityList/city/Cairo")).andExpect(status().isOk());
	}

	@Test
	public void should_Return400_When_BadRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/cityList/editCity/Berlin")).andExpect(status().isBadRequest());
	}

	@Test  // no JWT token -> status is 401 
	public void should_NotAllowAccessTo_SecuredApis_EditCity() throws Exception {
		String requestJson = mapper.writeValueAsString(DataFixtures.getCityPayload("Berlin'", "new_photo_url"));
		mvc.perform(MockMvcRequestBuilders.post("/api/cityList/editCity/Berlin").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isUnauthorized());
	}

	@Test  // with valid JWT token -> status is 200
	public void should_AllowAccessWithValidToken_ToSecuredApis_EditCity() throws Exception { 
		String requestJson = mapper.writeValueAsString(DataFixtures.getCityPayload("Berlin'", "new_photo_url"));
		String jwtToken = jwtUtils.generateToken("kuehneNagel");
		mvc.perform(MockMvcRequestBuilders.post("/api/cityList/editCity/Berlin").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson).header("Authorization", "Bearer "+jwtToken)).andExpect(status().isOk());
	}
}
