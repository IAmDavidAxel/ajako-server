package com.example.ajakoserver.domain.user;

import com.example.ajakoserver.api.dto.user.ClientDto;
import com.example.ajakoserver.domain.user.credential.CredentialFactory;
import com.example.ajakoserver.domain.user.password.PasswordFactory;
import com.example.ajakoserver.domain.user.profile.UserProfileFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory implements UserFactory<User,ClientDto>{
	private final CredentialFactory credentialFactory;
	private final UserProfileFactory userProfileFactory;
	private final PasswordFactory passwordFactory;

	public ClientFactory(CredentialFactory credentialFactory, UserProfileFactory userProfileFactory, PasswordFactory passwordFactory) {

		this.credentialFactory = credentialFactory;
		this.userProfileFactory = userProfileFactory;
		this.passwordFactory = passwordFactory;
	}

	@Override
	public User create(ClientDto clientDto) {
		return null;
	}
}
