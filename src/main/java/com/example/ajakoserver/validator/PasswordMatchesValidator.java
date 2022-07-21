package com.example.ajakoserver.validator;

import com.example.ajakoserver.api.dto.user.ClientDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, ClientDto> {

	@Override
	public boolean isValid(final ClientDto clientDto, final ConstraintValidatorContext context){

		return clientDto.getPassword().equals(clientDto.getMatchingPassword());
	}
}
