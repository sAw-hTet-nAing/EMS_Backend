package com.saw.emsbackend.controllers;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.exception.AuthenticationResponse;
import com.saw.emsbackend.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody UserDto userDto) {
        AuthenticationResponse response = userService.authenticate(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
