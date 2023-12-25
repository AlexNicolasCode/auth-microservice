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
import com.ms.user.domain.model.Token;
import com.ms.user.domain.usecase.CheckEmailAlreadyUsed;
import com.ms.user.domain.usecase.SaveUser;
import com.ms.user.domain.usecase.SendWelcomeEmail;
import com.ms.user.domain.usecase.UpdateToken;
import com.ms.user.presentation.protocol.Controller;

@RestController
public class SaveUserController implements Controller<SaveUserDto, ResponseEntity<Object>> {

	public SaveUserController(
		CheckEmailAlreadyUsed checkEmailAlreadyUsed,
		SaveUser saveUser,
		UpdateToken updateToken,
		SendWelcomeEmail sendWelcomeEmail
	) {
		this.checkEmailAlreadyUsed = checkEmailAlreadyUsed;
		this.saveUser = saveUser;
		this.updateToken = updateToken;
		this.sendWelcomeEmail = sendWelcomeEmail;
	}
	
	private CheckEmailAlreadyUsed checkEmailAlreadyUsed;
	private SaveUser saveUser;
	private UpdateToken updateToken;
	private SendWelcomeEmail sendWelcomeEmail;
    
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public ResponseEntity<Object> handle(@RequestBody SaveUserDto userDto) {
		try {
			Map<String, Object> response = new HashMap<String, Object>();
			String errorMessage = userDto.getError();
			if (errorMessage != null) {
				response.put("error", errorMessage);
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
			DefaultReturn<Boolean> isAlreadyUsed = this.checkEmailAlreadyUsed.checkEmailAlreadyUsed(userDto.getEmail());
			if (isAlreadyUsed.getContent()) {
				return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
			}
			this.saveUser.save(
				userDto.getName(),
				userDto.getEmail(),
				userDto.getPassword()
			);
			Token token = updateToken.update(userDto.getEmail());
			response.put("token", token.getValue());
			sendWelcomeEmail.sendEmail(userDto.getName(), userDto.getEmail());
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		} catch (Exception error) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
