package com.K955.Blog_App.Dto.Auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(

        @NotBlank
        String username,

        @Size(min = 1, max = 50)
        String name,

        @Size(min = 4, max = 30)
        String password
) {
}
