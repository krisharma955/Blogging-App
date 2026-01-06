package com.K955.Blog_App.Dto.Blog;

public record BlogRequest(
        String title,
        String content,
        Boolean isPublic
) {
}
