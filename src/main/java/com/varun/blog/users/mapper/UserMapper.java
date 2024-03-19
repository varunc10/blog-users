package com.varun.blog.users.mapper;

import com.varun.blog.users.dto.UserResponse;
import com.varun.blog.users.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  final private User user;

  public UserResponse convertUserToUserResponse(User user) {
    return UserResponse.builder()
        .userName(user.getUserName())
        .comments(user.getComments())
        .likes(user.getLikes())
        .build();
  }
}
