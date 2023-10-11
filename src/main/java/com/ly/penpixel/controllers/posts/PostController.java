package com.ly.penpixel.controllers.posts;

import com.ly.penpixel.controllers.IController;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.useCases.posts.PostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController implements IController<Post> {

  private final PostUseCase postUseCase;

  @Override
  public ResponseEntity<Post> findById(UUID id) {
    return ResponseEntity.ok(postUseCase.findById(id));

  }

  @Override
  public ResponseEntity<List<Post>> findAll(@RequestParam(required = false, defaultValue = "20") Integer limit,
                                            @RequestParam(required = false, defaultValue = "1") Integer offset) {
    return ResponseEntity.ok(postUseCase.findAll(limit, offset));
  }

  @Override
  public ResponseEntity<Post> create(Post post) {
    return ResponseEntity.status(HttpStatus.CREATED).body(postUseCase.create(post));
  }

  @Override
  public ResponseEntity<Post> update(UUID id, Post post) {
    return ResponseEntity.ok(postUseCase.update(post));
  }

  @Override
  public ResponseEntity<Void> delete(UUID id) {
    postUseCase.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
