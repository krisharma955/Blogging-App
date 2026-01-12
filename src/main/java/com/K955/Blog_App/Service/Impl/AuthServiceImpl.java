package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Auth.AuthResponse;
import com.K955.Blog_App.Dto.Auth.LoginRequest;
import com.K955.Blog_App.Dto.Auth.LoginResponse;
import com.K955.Blog_App.Dto.Auth.SignupRequest;
import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Error.BadRequestException;
import com.K955.Blog_App.Mapper.UserMapper;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Security.AuthUtil;
import com.K955.Blog_App.Service.AuthService;
import com.K955.Blog_App.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    UserMapper userMapper;

    UserService userService;

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
    public LoginResponse login(LoginRequest request, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        User user = (User) authentication.getPrincipal();

        if(user == null) {
            throw new BadRequestException("User not found with username " +request.username());
        }

        String accessToken = authUtil.generateAccessToken(user);

        String refreshToken = authUtil.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new LoginResponse(accessToken, refreshToken, userMapper.toUserProfileResponseFromUser(user));
    }

    @Override
    public LoginResponse refresh(Long userId, HttpServletRequest request) {

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(
                        () -> new AuthenticationServiceException("Refresh token not found inside cookies")
                );

        User user = userService.findUserById(userId);

        String accessToken = authUtil.generateAccessToken(user);

        return new LoginResponse(accessToken, refreshToken, userMapper.toUserProfileResponseFromUser(user));
    }
}
