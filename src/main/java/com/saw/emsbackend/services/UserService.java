package com.saw.emsbackend.services;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.exception.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse createUser(UserDto userDto);

    UserDto getUser(Long id);

    UserDto findByUsername(String username);

    AuthenticationResponse authenticate(UserDto userDto);

}
