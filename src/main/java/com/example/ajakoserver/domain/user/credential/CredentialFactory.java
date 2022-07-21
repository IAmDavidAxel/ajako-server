package com.example.ajakoserver.domain.user.credential;

import com.example.ajakoserver.domain.user.password.Password;
import org.springframework.stereotype.Component;

@Component
public class CredentialFactory {
	public Credential create(Password password, Token token, AccessLevel clientAccessLevel) {
		return null;
	}
}
