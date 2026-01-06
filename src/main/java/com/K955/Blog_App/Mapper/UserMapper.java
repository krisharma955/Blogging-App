package com.K955.Blog_App.Mapper;

import com.K955.Blog_App.Dto.Auth.UserProfileResponse;
import com.K955.Blog_App.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfileResponse toUserProfileResponseFromUser(User user);

}
