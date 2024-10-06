package com.saw.emsbackend.services;

import com.saw.emsbackend.dto.UserDto;
import com.saw.emsbackend.exception.AuthenticationResponse;
import com.saw.emsbackend.exception.ResourceNotFoundException;
import com.saw.emsbackend.mapper.UserMapper;
import com.saw.emsbackend.models.User;
import com.saw.emsbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse createUser(UserDto userDto) {
        userRepository.findByUsername(userDto.getUsername())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("Username already exists");
                });
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.toUser(userDto);
        User saveUser = userRepository.save(user);
        final String jwt = jwtService.generateToken(saveUser);
        return new AuthenticationResponse(jwt, UserMapper.toUserDto(saveUser));

    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toUserDto(user);
    }/**/

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toUserDto(user);

    }

    @Override
    public AuthenticationResponse authenticate(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );
        UserDto user = findByUsername(userDto.getUsername());
        final String jwt = jwtService.generateToken(UserMapper.toUser(user));
        return new AuthenticationResponse(jwt, user);

    }


}
