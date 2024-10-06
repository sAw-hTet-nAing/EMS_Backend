package com.saw.emsbackend.mapper;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.models.User;


public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getUserRole());
    }

    public static User toUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getRole());
    }
}
