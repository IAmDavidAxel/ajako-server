package com.example.ajakoserver.api.dto.user;

import com.example.ajakoserver.domain.user.UserInfo;
import lombok.Value;

@Value
public class JwtAuthenticationResponse {

	private String accessToken;
	private UserInfo userInfo;
}
