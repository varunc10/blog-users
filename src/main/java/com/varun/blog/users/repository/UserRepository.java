package com.varun.blog.users.repository;

import com.varun.blog.users.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  User findUserById(String id);
  User findUserByUserName(String userName);
}
