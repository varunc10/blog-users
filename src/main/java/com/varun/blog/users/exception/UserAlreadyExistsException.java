package com.varun.blog.users.exception;

public class UserAlreadyExistsException extends RuntimeException{
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
