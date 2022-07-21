package com.example.ajakoserver.api.resource.user;

import com.example.ajakoserver.api.dto.user.ApiResponse;
import com.example.ajakoserver.api.dto.user.ClientDto;
import com.example.ajakoserver.application.service.token.TokenProvider;
import com.example.ajakoserver.application.service.user.UserAlreadyExistAuthenticationException;
import com.example.ajakoserver.application.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserJsonResource {

	AuthenticationManager authenticationManager;
	private UserService userService;

	TokenProvider tokenProvider;

	public UserJsonResource(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> create(@RequestBody ClientDto clientDto) throws Exception {
		try{
			userService.create(clientDto);
		}catch (UserAlreadyExistAuthenticationException e){
			log.error("Exception Occured", e);
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}


		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));

	}
}
