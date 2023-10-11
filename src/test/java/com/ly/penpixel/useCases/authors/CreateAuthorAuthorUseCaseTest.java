package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.infra.entities.authors.AuthorDataMapper;
import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
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

class CreateAuthorAuthorUseCaseTest {
  @Mock
  private AuthorDAO authorDAO;

  @Mock
  private AuthorDomainMapper authorDomainMapper;
  @Mock
  private AuthorDataMapper authorDataMapper;

  @InjectMocks
  private CreateAuthorUseCase createAuthorUseCase;

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

    when(authorDAO.findById(any())).thenReturn(authorDataModel);
    when(authorDomainMapper.toDomain(any(AuthorDataModel.class))).thenReturn(author);
    when(authorDataMapper.toModel(any(Author.class))).thenReturn(authorDataModel);
    when(authorDAO.create(any(AuthorDataModel.class))).thenReturn(authorId);

    Author result = createAuthorUseCase.execute(author);
    verify(authorDAO, times(1)).create(authorDataModel);
    verify(authorDAO, times(1)).findById(authorId);
    verify(authorDomainMapper, times(1)).toDomain(authorDataModel);
    assertEquals(authorDataModel.getId(), result.getId());
    assertEquals(authorDataModel.getName(), result.getName());
    assertEquals(authorDataModel.getEmail(), result.getEmail());
  }
}