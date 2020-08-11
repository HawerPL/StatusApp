package com.hawer.app.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.hawer.app.dto.UserDto;
import com.hawer.app.entity.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
	
	UserDto userToUserDto(User user);
	User userDtoToUser(UserDto userDto);
	
	List<User> toUserList(List<UserDto> userDto);
	List<UserDto> toUserDtoList(List<User> users);
	
}
