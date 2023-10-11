package com.ly.penpixel.controllers.posts;

import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.useCases.posts.PostUseCase;
import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostControllerTest {

  @Mock
  private PostUseCase postUseCase;

  @InjectMocks
  private PostController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  private final Post postTest = PostUtils.generatePostDomain(null);

  private final List<Post> postDataModelList = Arrays.asList(
      PostUtils.generatePostDomain(null),
      PostUtils.generatePostDomain(null),
      PostUtils.generatePostDomain(null)
  );

  @Test
  void findById() {
    Mockito.when(postUseCase.findById(any(UUID.class))).thenReturn(postTest);

    var responseEntity = controller.findById(UUID.randomUUID());

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(postTest.getContent(), responseEntity.getBody().getContent());
    assertEquals(postTest.getPublishDate(), responseEntity.getBody().getPublishDate());
  }

  @Test
  void findAll() {
    Mockito.when(postUseCase.findAll(1,3)).thenReturn(postDataModelList);

    var responseEntity = controller.findAll(1,3);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size());
  }

  @Test
  void create() {
    Mockito.when(postUseCase.create(any(Post.class))).thenReturn(postTest);

    var responseEntity = controller.create(postTest);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(postTest.getContent(), responseEntity.getBody().getContent());
    assertEquals(postTest.getPublishDate(), responseEntity.getBody().getPublishDate());
  }

  @Test
  void update() {
    Mockito.when(postUseCase.update(any(Post.class))).thenReturn(postTest);

    var responseEntity = controller.update(postTest.getId(), postTest);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(Objects.requireNonNull(responseEntity.getBody()).getId());
    assertEquals(postTest.getContent(), responseEntity.getBody().getContent());
    assertEquals(postTest.getPublishDate(), responseEntity.getBody().getPublishDate());
  }

  @Test
  void delete() {
    doNothing().when(postUseCase).delete(any(UUID.class));

    var responseEntity = controller.delete(postTest.getId());

    verify(postUseCase, times(1)).delete(postTest.getId());
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }
}