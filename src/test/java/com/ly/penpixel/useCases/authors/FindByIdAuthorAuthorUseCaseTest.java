package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.repositories.authors.AuthorDAO;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindByIdAuthorAuthorUseCaseTest {

  @Mock
  private AuthorDAO authorDAO;

  @Mock
  private AuthorDomainMapper authorDomainMapper;

  @InjectMocks
  private FindByIdAuthorUseCase findByIDAuthorUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute() {
    UUID authorId = UUID.randomUUID();
    var authorDataModel = AuthorUtils.generateAuthorDataModel(authorId);
    var author = Author.builder()
        .id(authorDataModel.getId())
        .name(authorDataModel.getName())
        .email(authorDataModel.getEmail())
        .build();

    when(authorDAO.findById(authorId)).thenReturn(authorDataModel);
    when(authorDomainMapper.toDomain(authorDataModel)).thenReturn(author);

    Author result = findByIDAuthorUseCase.execute(authorId);
    verify(authorDAO, times(1)).findById(authorId);
    verify(authorDomainMapper, times(1)).toDomain(authorDataModel);
    assertEquals(authorDataModel.getId(), result.getId());
    assertEquals(authorDataModel.getName(), result.getName());
    assertEquals(authorDataModel.getEmail(), result.getEmail());
  }
}