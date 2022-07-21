package com.example.ajakoserver.api.resource.user;

import com.example.ajakoserver.api.dto.user.ClientDto;
import com.example.ajakoserver.application.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

public class UserJsonResourceTest {

	private UserJsonResource userJsonResource;

	@MockBean
	private UserService userService;
	private ClientDto clientDto;

	@BeforeEach
	public void setUp() throws Exception{
		userJsonResource = new UserJsonResource(userService);
	}

	@Test
	public void whenCreatingANewUser_thenDelegateToService()throws Exception{

		userJsonResource.create(clientDto);

		verify(userService).create(clientDto);

	}

}
