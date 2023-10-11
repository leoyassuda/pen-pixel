package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.useCases.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorUseCase implements IUseCase<Author> {

  private final CreateAuthorUseCase createAuthorUseCase;
  private final FindAllAuthorsUseCase findAllAuthorsUseCase;
  private final FindByIdAuthorUseCase findByIDAuthorUseCase;
  private final UpdateAuthorUseCase updateAuthorUseCase;
  private final DeleteAuthorUseCase deleteAuthorUseCase;

  @Cacheable(value = "authorCache", key = "#id")
  @Override
  public Author findById(UUID id) {
    return findByIDAuthorUseCase.execute(id);
  }

  @Cacheable(value = "authorCache", key = "'allAuthors-' + #limit + '-' + #offset")
  @Override
  public List<Author> findAll(Integer limit, Integer offset) {
    return findAllAuthorsUseCase.execute(limit, offset);
  }

  @CacheEvict(value = "authorCache ", allEntries = true)
  @Override
  public Author create(Author author) {
    return createAuthorUseCase.execute(author);
  }

  @CachePut(value = "authorCache", key = "#author.id")
  @Override
  public Author update(Author author) {
    return updateAuthorUseCase.execute(author);
  }

  @Override
  public void delete(UUID id) {
    deleteAuthorUseCase.execute(id);
  }
}
