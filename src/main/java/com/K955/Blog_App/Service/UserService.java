package com.K955.Blog_App.Service;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import com.K955.Blog_App.Entity.User;

import java.util.Optional;

public interface UserService {
    UserProfileResponse getProfile(Long userId);

    User findUserById(Long userId);

    User saveNewUser(User user);
}
