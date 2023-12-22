package com.ms.user.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.user.domain.model.User;
import com.ms.user.domain.usecase.SaveUser;
import com.ms.user.domain.usecase.SendWelcomeEmail;
import com.ms.user.domain.usecase.UpdateToken;
import com.ms.user.presentation.dto.SaveUserDto;
import com.ms.user.presentation.protocol.Controller;
import com.ms.user.presentation.protocol.Validator;

import jakarta.validation.Valid;

@RestController
public class SaveUserController implements Controller<SaveUserDto, Errors, ResponseEntity<Object>> {

	public SaveUserController(
		SaveUser saveUser,
		UpdateToken updateToken,
		Validator<Errors> validator,
		SendWelcomeEmail sendWelcomeEmail
	) {
		this.saveUser = saveUser;
		this.updateToken = updateToken;
		this.validator = validator;
		this.sendWelcomeEmail = sendWelcomeEmail;
	}
	
	private SaveUser saveUser;
	private UpdateToken updateToken;
	private Validator<Errors> validator;
	private SendWelcomeEmail sendWelcomeEmail;
    
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> handle(@Valid @RequestBody SaveUserDto userDto, Errors errors) {
		Map<String, Object> response = new HashMap<String, Object>();
		Object error = validator.validate(errors);
		if (error != null) {
			return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		Long userId = saveUser.save(user);
		String token = updateToken.update(userId);
		response.put("token", token);
		sendWelcomeEmail.sendEmail(user);
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}    
}
