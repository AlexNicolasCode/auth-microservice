package com.ms.user.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.domain.dto.AuthenticationDto;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.model.Token;
import com.ms.user.domain.usecase.Authenticate;
import com.ms.user.presentation.protocol.Controller;

@RestController
public class AuthenticationController implements Controller<AuthenticationDto, ResponseEntity<Object>> {

	public AuthenticationController(Authenticate authenticate) {
		this.authenticate = authenticate;
	}
	
	private Authenticate authenticate;
    
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Object> handle(@RequestBody AuthenticationDto authenticationDto) {
		try {
			Map<String, Object> response = new HashMap<String, Object>();
			String errorMessage = authenticationDto.getError();
			if (errorMessage != null) {
				response.put("error", errorMessage);
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
			DefaultReturn<Token> result = this.authenticate.auth(
				authenticationDto.getEmail(),
				authenticationDto.getPassword()
			);
			if (result.getError() != null) {
				response.put("error", result.getError());
				return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
			} 
			Token token = result.getContent();
			response.put("token", token.getValue());
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		} catch (Exception error) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
