package com.K955.Blog_App.Dto.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @Email @NotBlank
        String email,

        @Size(min = 4, max = 30)
        String password
) {
}
