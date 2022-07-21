package com.example.ajakoserver.domain.user;

public interface UserFactory<User,UserDto> {

	User create(UserDto userDto) ;
}
