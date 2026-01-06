package com.K955.Blog_App.Dto.Auth;

public record AuthResponse(
        String token,
        UserProfileResponse user
) {
}
