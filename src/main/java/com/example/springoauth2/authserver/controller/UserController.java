package com.example.springoauth2.authserver.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	
	@GetMapping("/me")
	public ResponseEntity<Object> getUserMe(Principal principal, Authentication auth){
		Object response = oAuth2RestTemplate.getForObject("http://localhost:8080/users", Object.class);
		return  ResponseEntity.ok(response);
	}

}
