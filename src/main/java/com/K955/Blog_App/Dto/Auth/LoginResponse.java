package com.K955.Blog_App.Dto.Auth;

public record LoginResponse(
        String accessToken,
        String refreshToken,
        UserProfileResponse user
) {
}
