package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.repositories.authors.AuthorDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindAllAuthorsUseCase {

  private final AuthorDAO authorDAO;
  private final AuthorDomainMapper authorDomainMapper;

  public List<Author> execute(Integer limit, Integer offset) {
    return authorDomainMapper.toDomain(authorDAO.findAll(limit, offset));
  }
}
