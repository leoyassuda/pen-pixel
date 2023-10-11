package com.ly.penpixel.domains.authors;

import com.ly.penpixel.domains.posts.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

  @Serial
  private static final long serialVersionUID = -8084372047020311248L;

  private UUID id;

  @NotBlank(message = "{error.author.name.required}")
  private String name;

  @NotBlank(message = "{error.author.email.required}")
  @Email
  private String email;

  private List<Post> postList;
}
