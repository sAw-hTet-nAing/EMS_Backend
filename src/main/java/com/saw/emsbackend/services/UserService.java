package com.saw.emsbackend.services;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.response.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse createUser(UserDto userDto);

   
    UserDto findByUsername(String username);

    AuthenticationResponse authenticate(UserDto userDto);

}
