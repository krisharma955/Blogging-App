package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Auth.AuthResponse;
import com.K955.Blog_App.Dto.Auth.LoginRequest;
import com.K955.Blog_App.Dto.Auth.SignupRequest;
import com.K955.Blog_App.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
