package com.K955.Blog_App.Dto.Blog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BlogRequest(

        @Size(min = 1)
        String title,

        @NotBlank
        String content,

        @NotBlank
        Boolean isPublic
) {
}
