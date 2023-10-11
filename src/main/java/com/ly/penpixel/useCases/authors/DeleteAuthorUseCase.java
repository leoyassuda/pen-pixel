package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.repositories.authors.AuthorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeleteAuthorUseCase {

  private final AuthorDAO authorDAO;

  public void execute(UUID id) {
    authorDAO.delete(id);
  }
}
