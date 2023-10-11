package com.ly.penpixel.controllers.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.useCases.authors.AuthorUseCase;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

  @Mock
  private AuthorUseCase authorUseCase;

  @InjectMocks
  private AuthorController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private final Author authorTest = AuthorUtils.generateAuthor(null);

  private final List<Author> authorDataModelList = Arrays.asList(
      AuthorUtils.generateAuthor(null),
      AuthorUtils.generateAuthor(null),
      AuthorUtils.generateAuthor(null)
  );

  @Test
  void findById() {
    Mockito.when(authorUseCase.findById(any(UUID.class))).thenReturn(authorTest);

    var responseEntity = controller.findById(UUID.randomUUID());

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(authorTest.getName(), responseEntity.getBody().getName());
    assertEquals(authorTest.getEmail(), responseEntity.getBody().getEmail());
  }

  @Test
  void findAll() {
    Mockito.when(authorUseCase.findAll(1, 3)).thenReturn(authorDataModelList);

    var responseEntity = controller.findAll(1,3);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size());
  }

  @Test
  void create() {
    Mockito.when(authorUseCase.create(any(Author.class))).thenReturn(authorTest);

    var responseEntity = controller.create(authorTest);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(authorTest.getName(), responseEntity.getBody().getName());
    assertEquals(authorTest.getEmail(), responseEntity.getBody().getEmail());
  }

  @Test
  void update() {
    Mockito.when(authorUseCase.update(any(Author.class))).thenReturn(authorTest);

    var responseEntity = controller.update(authorTest.getId(), authorTest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(authorTest.getName(), responseEntity.getBody().getName());
    assertEquals(authorTest.getEmail(), responseEntity.getBody().getEmail());
  }

  @Test
  void delete() {
    doNothing().when(authorUseCase).delete(any(UUID.class));

    var responseEntity = controller.delete(authorTest.getId());

    verify(authorUseCase, times(1)).delete(authorTest.getId());
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }
}