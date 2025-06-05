package com.LoginRegister.LoginRegisterServer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class AppController {
	
	 private final String API_KEY = "35913f19ab69da02d9c8de9646346bb9";
	    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

	    @GetMapping(value = "/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getWeather(@PathVariable String city) {
	        System.out.println("Received request for city: " + city);

	        RestTemplate restTemplate = new RestTemplate();
	        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";

	        try {
	            String response = restTemplate.getForObject(url, String.class);
	            System.out.println("API Response: " + response);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            System.out.println("Error fetching weather: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"City not found\"}");
	        }
	    }

}
