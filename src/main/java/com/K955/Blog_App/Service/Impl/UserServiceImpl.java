package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Error.ResourceNotFoundException;
import com.K955.Blog_App.Mapper.UserMapper;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserProfileResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId, "User"));

        return userMapper.toUserProfileResponseFromUser(user);
    }
}
