package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Auth.AuthResponse;
import com.K955.Blog_App.Dto.Auth.LoginRequest;
import com.K955.Blog_App.Dto.Auth.SignupRequest;
import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Error.BadRequestException;
import com.K955.Blog_App.Mapper.UserMapper;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Security.AuthUtil;
import com.K955.Blog_App.Service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    AuthUtil authUtil;

    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signup(SignupRequest request) {

        userRepository.findByUsername(request.username()).ifPresent(
                user -> {
                    throw new BadRequestException("User already exists with username " + request.username());
                }
        );

        User user = userMapper.toUserFromSignupRequest(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token, userMapper.toUserProfileResponseFromUser(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();

        if(user == null) {
            throw new BadRequestException("User not found with username " +request.username());
        }

        String token = authUtil.generateAccessToken(user);

        return new AuthResponse(token, userMapper.toUserProfileResponseFromUser(user));
    }
}
