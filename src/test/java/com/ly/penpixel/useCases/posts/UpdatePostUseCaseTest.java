package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.infra.entities.posts.PostDataMapper;
import com.ly.penpixel.infra.entities.posts.PostDataModel;
import com.ly.penpixel.repositories.posts.PostDAO;
import com.ly.penpixel.useCases.posts.UpdatePostUseCase;
import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdatePostUseCaseTest {

  @Mock
  private PostDAO postDAO;

  @Mock
  private PostDomainMapper postDomainMapper;
  @Mock
  private PostDataMapper postDataMapper;

  @InjectMocks
  private UpdatePostUseCase updatePostUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute() {
    var postDataModel = PostUtils.generatePostDataModel(null);
    var post = Post.builder()
        .id(postDataModel.getId())
        .content(postDataModel.getContent())
        .publishDate(postDataModel.getPublishDate())
        .author(Author.builder()
            .id(postDataModel.getAuthorDataModel().getId())
            .name(postDataModel.getAuthorDataModel().getName())
            .email(postDataModel.getAuthorDataModel().getEmail())
            .build())
        .build();
    var postId = postDataModel.getId();

    when(postDAO.findById(any())).thenReturn(postDataModel);
    when(postDomainMapper.toDomain(any(PostDataModel.class))).thenReturn(post);
    when(postDataMapper.toModel(any(Post.class))).thenReturn(postDataModel);
    when(postDAO.update(any(PostDataModel.class))).thenReturn(1);

    Post result = updatePostUseCase.execute(post);
    verify(postDAO, times(1)).update(postDataModel);
    verify(postDAO, times(1)).findById(postId);
    verify(postDomainMapper, times(1)).toDomain(postDataModel);
    assertEquals(postDataModel.getId(), result.getId());
    assertEquals(postDataModel.getContent(), result.getContent());
  }
}