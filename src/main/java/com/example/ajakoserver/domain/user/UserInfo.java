package com.example.ajakoserver.domain.user;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {

	private String id, displayName, email;
	private List<String> roles;

}
