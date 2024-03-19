package com.varun.blog.users.model;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.stereotype.Component;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Document(collection = "users")
public class User {
  @Id
  @Field(targetType = FieldType.OBJECT_ID)
  @Valid
  String id;
  @Valid
  String userName;
  @Valid
  List<String> comments;
  @Valid
  Long likes;
}
