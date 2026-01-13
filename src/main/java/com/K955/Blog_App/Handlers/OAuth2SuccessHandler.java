package com.K955.Blog_App.Handlers;

import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Security.AuthUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    UserRepository userRepository;

    AuthUtil authUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

        log.info(oAuth2User.getAttribute("email"));

        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByUsername(email)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .username(email)
                            .name(oAuth2User.getAttribute("name"))
                            .build();

                    return userRepository.save(newUser);
                });

        String accessToken = authUtil.generateAccessToken(user);

        String refreshToken = authUtil.generateRefreshToken(user);
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        String frontendUrl = "http://localhost:8080/home.html?token="+accessToken;

        response.sendRedirect(frontendUrl);
    }
}
