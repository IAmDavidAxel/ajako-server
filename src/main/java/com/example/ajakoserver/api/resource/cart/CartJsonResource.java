package com.example.ajakoserver.api.resource.cart;

import com.example.ajakoserver.api.dto.CartDto;
import com.example.ajakoserver.application.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController("/cart")
public class CartJsonResource {


	@Autowired
	private UserService userService;

	public CartJsonResource(UserService userService) {

		this.userService = userService;
	}

	public ResponseEntity<Object> addToCart(CartDto cartDto) throws Exception{

		userService.addToCart(cartDto);

		return null;
	}
}
