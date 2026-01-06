package com.K955.Blog_App.Service;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import org.jspecify.annotations.Nullable;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}
