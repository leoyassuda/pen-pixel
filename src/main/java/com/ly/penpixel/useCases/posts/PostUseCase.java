package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.useCases.IUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostUseCase implements IUseCase<Post> {

  private final CreatePostUseCase createPostUseCase;
  private final FindByIdPostUseCase findByIdPostUseCase;
  private final FindAllPostUseCase findAllPostUseCase;
  private final UpdatePostUseCase updatePostUseCase;
  private final DeletePostUseCase deletePostUseCase;

  @Cacheable(value = "postCache", key = "#id")
  @Override
  public Post findById(UUID id) {
    return findByIdPostUseCase.execute(id);
  }

  @Cacheable(value = "postCache", key = "'allAuthors-' + #limit + '-' + #offset")
  @Override
  public List<Post> findAll(Integer limit, Integer offset) {
    return findAllPostUseCase.execute(limit, offset);
  }

  @CacheEvict(value = "postCache ", allEntries = true)
  @Override
  public Post create(Post post) {
    return createPostUseCase.execute(post);
  }

  @CachePut(value = "postCache", key = "#post.id")
  @Override
  public Post update(Post post) {
    return updatePostUseCase.execute(post);
  }

  @Override
  public void delete(UUID id) {
    deletePostUseCase.execute(id);
  }
}
