package com.ms.user.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.domain.dto.SaveUserDto;
import com.ms.user.domain.model.DefaultReturn;
import com.ms.user.domain.usecase.SaveUser;
import com.ms.user.domain.usecase.SendWelcomeEmail;
import com.ms.user.domain.usecase.UpdateToken;
import com.ms.user.presentation.protocol.Controller;

@RestController
public class SaveUserController implements Controller<SaveUserDto, ResponseEntity<Object>> {

	public SaveUserController(
		SaveUser saveUser,
		UpdateToken updateToken,
		SendWelcomeEmail sendWelcomeEmail
	) {
		this.saveUser = saveUser;
		this.updateToken = updateToken;
		this.sendWelcomeEmail = sendWelcomeEmail;
	}
	
	private SaveUser saveUser;
	private UpdateToken updateToken;
	private SendWelcomeEmail sendWelcomeEmail;
    
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> handle(@RequestBody SaveUserDto userDto) {
		Map<String, Object> response = new HashMap<String, Object>();
		String errorMessage = userDto.getError();
		if (errorMessage != null) {
			response.put("error", errorMessage);
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
		}
		DefaultReturn<Long> result = this.saveUser.save(
			userDto.getName(),
			userDto.getEmail(),
			userDto.getPassword()
		);
		String token = updateToken.update(result.getContent());
		response.put("token", token);
		sendWelcomeEmail.sendEmail(userDto.getName(), userDto.getEmail());
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
}
