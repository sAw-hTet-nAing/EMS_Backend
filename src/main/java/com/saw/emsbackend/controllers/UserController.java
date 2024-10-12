package com.saw.emsbackend.controllers;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.response.AuthenticationResponse;
import com.saw.emsbackend.services.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> createUser(@Valid @RequestBody UserDto userDto) {
        AuthenticationResponse response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@NotNull @RequestParam("username") String username,
                                                            @NotNull @RequestParam("password") String password) {

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);

        AuthenticationResponse response = userService.authenticate(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
