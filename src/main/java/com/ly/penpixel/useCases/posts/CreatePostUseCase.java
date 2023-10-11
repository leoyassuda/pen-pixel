package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.infra.entities.posts.PostDataMapper;
import com.ly.penpixel.repositories.posts.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreatePostUseCase {

  private final PostDAO postDAO;
  private final PostDataMapper postDataMapper;
  private final PostDomainMapper postDomainMapper;

  public Post execute(Post post) {
    UUID id = postDAO.create(postDataMapper.toModel(post));
    return postDomainMapper.toDomain(postDAO.findById(id));
  }
}
