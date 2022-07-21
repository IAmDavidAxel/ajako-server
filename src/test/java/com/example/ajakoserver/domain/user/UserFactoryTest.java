package com.example.ajakoserver.domain.user;

import com.example.ajakoserver.api.dto.user.ClientDto;
import com.example.ajakoserver.domain.user.ClientFactory;
import com.example.ajakoserver.domain.user.credential.AccessLevel;
import com.example.ajakoserver.domain.user.credential.CredentialFactory;
import com.example.ajakoserver.domain.user.credential.Token;
import com.example.ajakoserver.domain.user.password.Password;
import com.example.ajakoserver.domain.user.password.PasswordFactory;
import com.example.ajakoserver.domain.user.profile.UserProfileFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class UserFactoryTest {

	private static final AccessLevel CLIENT_ACCESS_LEVEL = AccessLevel.CLIENT;
	private ClientFactory clientFactory;
	@Mock
	private CredentialFactory credentialFactory;
	@Mock
	private UserProfileFactory userProfile;
	@Mock
	private PasswordFactory passwordFactory;
	private ClientDto clientDto;
	private Password password;
	private Token token;


	@BeforeEach
	public void setUp()throws Exception{

		clientFactory = new ClientFactory(credentialFactory,userProfile,passwordFactory);
	}

	@Test
	public void WhenCreating_thenDelegateCredentialCreationToFactory() throws Exception{

		clientFactory.create(clientDto);

		verify(credentialFactory).create(password,token,CLIENT_ACCESS_LEVEL);

	}

}
