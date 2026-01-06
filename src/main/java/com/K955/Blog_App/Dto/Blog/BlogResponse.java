package com.K955.Blog_App.Dto.Blog;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;

import java.time.Instant;

public record BlogResponse(
        Long id,
        String title,
        Boolean isPublic,
        Instant createdAt,
        Instant updatedAt,
        UserProfileResponse owner
) {
}
