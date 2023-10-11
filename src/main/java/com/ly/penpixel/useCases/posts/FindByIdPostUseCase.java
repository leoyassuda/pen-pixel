package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.repositories.posts.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FindByIdPostUseCase {

  private final PostDAO postDAO;
  private final PostDomainMapper postDomainMapper;

  public Post execute(UUID id) {
    return postDomainMapper.toDomain(postDAO.findById(id));
  }
}
