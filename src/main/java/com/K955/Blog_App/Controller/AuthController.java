package com.K955.Blog_App.Controller;

import com.K955.Blog_App.Dto.Auth.AuthResponse;
import com.K955.Blog_App.Dto.Auth.LoginRequest;
import com.K955.Blog_App.Dto.Auth.SignupRequest;
import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import com.K955.Blog_App.Security.AuthUtil;
import com.K955.Blog_App.Service.AuthService;
import com.K955.Blog_App.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = authUtil.getCurrentUserId();
        return ResponseEntity.ok(userService.getProfile(userId));
    }

}
