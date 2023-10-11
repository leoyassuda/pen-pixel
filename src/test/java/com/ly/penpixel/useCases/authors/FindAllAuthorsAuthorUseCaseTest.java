package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.authors.AuthorDomainMapper;
import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.repositories.authors.AuthorDAO;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindAllAuthorsAuthorUseCaseTest {

  @Mock
  private AuthorDAO authorDAO;

  @Mock
  private AuthorDomainMapper authorDomainMapper;

  @InjectMocks
  private FindAllAuthorsUseCase findAllAuthorsUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenExecute_thenReturnCorrectList() {
    List<AuthorDataModel> authorDataModelList = new ArrayList<>();
    List<Author> authorList = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      AuthorDataModel authorDataModel = AuthorUtils.generateAuthorDataModel(null);
      authorDataModelList.add(authorDataModel);
    }
    for (int i = 1; i <= 10; i++) {
      Author author = AuthorUtils.generateAuthor(null);
      authorList.add(author);
    }

    when(authorDAO.findAll(1,20)).thenReturn(authorDataModelList);
    when(authorDomainMapper.toDomain(authorDataModelList)).thenReturn(authorList);

    var resultList = findAllAuthorsUseCase.execute(1,20);
    verify(authorDAO, times(1)).findAll(1,20);
    verify(authorDomainMapper, times(1)).toDomain(authorDataModelList);
    assertEquals(10, resultList.size());
  }
}