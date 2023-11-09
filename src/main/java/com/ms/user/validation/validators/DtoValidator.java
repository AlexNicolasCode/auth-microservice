package com.ms.user.validation.validators;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.ms.user.presentation.protocol.Validator;

@Component
public class DtoValidator implements Validator<Errors> {

    @Override
    public Object validate(Errors errors) {
        Map<String, Object> response = new HashMap<String, Object>();
        FieldError fieldError = errors.getFieldError();
		if (errors.hasErrors() && fieldError != null) {
			String message = fieldError.getDefaultMessage();
			String field = fieldError.getField();
			response.put("message", message);
			response.put("field", field);
			return response;
		}
        return null;
    }
}
