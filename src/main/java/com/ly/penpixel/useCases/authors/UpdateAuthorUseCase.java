package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.infra.entities.authors.AuthorDataMapper;
import com.ly.penpixel.repositories.authors.AuthorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateAuthorUseCase {

  private final AuthorDAO authorDAO;
  private final AuthorDomainMapper authorDomainMapper;
  private final AuthorDataMapper authorDataMapper;

  public Author execute(Author author) {
    authorDAO.update(authorDataMapper.toModel(author));
    return authorDomainMapper.toDomain(authorDAO.findById(author.getId()));
  }
}
