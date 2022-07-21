package com.example.ajakoserver.api.resource.cart;

import com.example.ajakoserver.api.dto.CartDto;
import com.example.ajakoserver.application.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class CartJsonResourceTest {

	private CartJsonResource cartJsonResource;

	@Mock
	private UserService userService;
	private CartDto cartDto;

	@BeforeEach
	public void setUp()throws Exception{
		cartJsonResource = new CartJsonResource(userService);
	}

	@Test
	public void whenAddingToCart_thenDelegateToClientService()throws Exception{

		cartJsonResource.addToCart(cartDto);

		verify(userService).addToCart(cartDto);
	}

}
