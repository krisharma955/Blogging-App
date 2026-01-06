package com.K955.Blog_App.Dto.Auth;

public record SignupRequest(
        String email,
        String name,
        String password
) {
}
