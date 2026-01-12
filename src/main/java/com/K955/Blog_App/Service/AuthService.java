package com.K955.Blog_App.Service;

import com.K955.Blog_App.Dto.Auth.AuthResponse;
import com.K955.Blog_App.Dto.Auth.LoginRequest;
import com.K955.Blog_App.Dto.Auth.LoginResponse;
import com.K955.Blog_App.Dto.Auth.SignupRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    LoginResponse login(LoginRequest request, HttpServletResponse response);

    LoginResponse refresh(Long userId, HttpServletRequest request);
}
