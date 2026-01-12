package com.K955.Blog_App.Controller;

import com.K955.Blog_App.Dto.Auth.*;
import com.K955.Blog_App.Security.AuthUtil;
import com.K955.Blog_App.Service.AuthService;
import com.K955.Blog_App.Service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService authService;

    UserService userService;

    AuthUtil authUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(request, response));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(HttpServletRequest request) {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(authService.refresh(userId, request));
    }

}
