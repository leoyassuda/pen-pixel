package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.repositories.posts.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeletePostUseCase {

  private final PostDAO postDAO;

  public void execute(UUID id) {
    postDAO.delete(id);
  }
}
