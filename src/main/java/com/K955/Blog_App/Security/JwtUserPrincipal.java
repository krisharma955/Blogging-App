package com.K955.Blog_App.Security;

public record JwtUserPrincipal(
        Long userId,
        String  username
) {
}
