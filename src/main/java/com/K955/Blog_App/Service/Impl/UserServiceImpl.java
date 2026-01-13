package com.K955.Blog_App.Service.Impl;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import com.K955.Blog_App.Entity.User;
import com.K955.Blog_App.Error.ResourceNotFoundException;
import com.K955.Blog_App.Mapper.UserMapper;
import com.K955.Blog_App.Repository.UserRepository;
import com.K955.Blog_App.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService, UserDetailsService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserProfileResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId.toString(), "User"));

        return userMapper.toUserProfileResponseFromUser(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId.toString(), "User"));
    }

    @Override
    public User saveNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->  new ResourceNotFoundException(username, "User"));
    }
}
