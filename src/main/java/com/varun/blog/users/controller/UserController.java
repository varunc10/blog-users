package com.varun.blog.users.controller;

import com.varun.blog.users.exception.UserAlreadyExistsException;
import com.varun.blog.users.wrapper.UserResponseWrapper;
import com.varun.blog.users.dto.UserResponse;
import com.varun.blog.users.exception.UserNotFoundException;
import com.varun.blog.users.mapper.UserMapper;
import com.varun.blog.users.model.User;
import com.varun.blog.users.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
  final private UserService userService;
  final private UserMapper userMapper;

  @GetMapping
  ResponseEntity<List<UserResponseWrapper>> getAllUsers() {
    ResponseEntity<List<UserResponseWrapper>> response;

    List<User> users = userService.getAllUsers();
    List<UserResponse> userResponses = users.stream()
        .map(userMapper::convertUserToUserResponse)
        .toList();

    List<UserResponseWrapper> userResponseWrapperList = userResponses.stream()
        .map(UserResponseWrapper::new)
        .toList();

    response = new ResponseEntity<>(userResponseWrapperList, HttpStatus.OK);
    return response;
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserResponseWrapper> getUserById(@PathVariable("userId") String userId) {
    ResponseEntity<UserResponseWrapper> response;
    try {
      User user = userService.getUserById(userId);
      UserResponse userResponse = userMapper.convertUserToUserResponse(user);
      response = new ResponseEntity<>(new UserResponseWrapper(userResponse), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      response = new ResponseEntity<>(new UserResponseWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  @GetMapping("/{userName}")
  public ResponseEntity<UserResponseWrapper> getUserByUserName(@PathVariable("userName") String userName) {
    ResponseEntity<UserResponseWrapper> response;
    try {
      User user = userService.getUserByUserName(userName);
      UserResponse userResponse = userMapper.convertUserToUserResponse(user);
      response = new ResponseEntity<>(new UserResponseWrapper(userResponse), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      response = new ResponseEntity<>(new UserResponseWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    return response;
  }
  @PostMapping
  public ResponseEntity<UserResponseWrapper> createUser(@RequestBody User user) {
    ResponseEntity<UserResponseWrapper> response;
    try {
      User createdUser = userService.createUser(user);
      UserResponse userResponse = userMapper.convertUserToUserResponse(createdUser);
      response = new ResponseEntity<>(new UserResponseWrapper(userResponse), HttpStatus.OK);
    } catch (UserAlreadyExistsException e) {
      response = new ResponseEntity<>(new UserResponseWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserResponseWrapper> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
    ResponseEntity<UserResponseWrapper> response;
    try {
      User createdUser = userService.updateUser(userId, user);
      UserResponse userResponse = userMapper.convertUserToUserResponse(createdUser);
      response = new ResponseEntity<>(new UserResponseWrapper(userResponse), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      response = new ResponseEntity<>(new UserResponseWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<UserResponseWrapper> deleteUser(@PathVariable("userId") String userId) {
    ResponseEntity<UserResponseWrapper> response;
    try {
      userService.deleteUserById(userId);
      response = new ResponseEntity<>(new UserResponseWrapper((String) null), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      response = new ResponseEntity<>(new UserResponseWrapper(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
