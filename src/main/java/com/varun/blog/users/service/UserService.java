package com.varun.blog.users.service;

import com.varun.blog.users.exception.UserAlreadyExistsException;
import com.varun.blog.users.exception.UserNotFoundException;
import com.varun.blog.users.model.User;
import com.varun.blog.users.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  final private UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserById(String id) throws UserNotFoundException {
    if (userRepository.findUserById(id) == null) {
      throw new UserNotFoundException("User Doesn't Exist");
    }
    return userRepository.findUserById(id);
  }

  public User getUserByUserName(String userName) throws UserNotFoundException{
    if (userRepository.findUserByUserName(userName) == null) {
      throw new UserNotFoundException("User Doesn't Exist");
    }
    return userRepository.findUserByUserName(userName);
  }

  public User createUser(User user) throws UserAlreadyExistsException{
    if (userRepository.findUserByUserName(user.getId()) != null) {
      throw new UserAlreadyExistsException("User Already Exists");
    }
    return userRepository.save(user);
  }

  public User updateUser(String id, User updatedUser) throws UserNotFoundException{
    User user = userRepository.findUserById(id);
    if (user == null) {
      throw new UserNotFoundException("User Doesn't Exist");
    }
    user.setUserName(updatedUser.getUserName());
    user.setComments(updatedUser.getComments());
    user.setLikes(updatedUser.getLikes());
    userRepository.save(user);
    return user;
  }

  public void deleteUserById(String id) throws UserNotFoundException{
    if (userRepository.findUserById(id) == null) {
      throw new UserNotFoundException("User Doesn't Exist");
    }
    userRepository.deleteById(id);
  }
}
