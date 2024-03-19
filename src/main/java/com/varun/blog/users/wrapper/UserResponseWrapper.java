package com.varun.blog.users.wrapper;

import com.varun.blog.users.dto.UserResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseWrapper {
  private String message;
  private UserResponse userResponse;

  public UserResponseWrapper(String message) {
    this.message = message;
  }

  public UserResponseWrapper(UserResponse userResponse) {
    this.userResponse = userResponse;
  }
}

