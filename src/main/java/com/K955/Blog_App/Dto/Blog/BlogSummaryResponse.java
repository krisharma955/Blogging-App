package com.K955.Blog_App.Dto.Blog;

import java.time.Instant;

public record BlogSummaryResponse(
        Long id,
        String title,
        Instant createdAt,
        Instant updatedAt
) {
}
