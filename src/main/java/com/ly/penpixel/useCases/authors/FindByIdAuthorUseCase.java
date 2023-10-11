package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.repositories.authors.AuthorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FindByIdAuthorUseCase {

  private final AuthorDAO authorDAO;
  private final AuthorDomainMapper authorDomainMapper;

  public Author execute(UUID id) {
    return authorDomainMapper.toDomain(authorDAO.findById(id));
  }
}
