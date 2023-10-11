package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.repositories.posts.PostDAO;

import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindByIdPostUseCaseTest {

  @Mock
  private PostDAO postDAO;

  @Mock
  private PostDomainMapper postDomainMapper;

  @InjectMocks
  private FindByIdPostUseCase findByIDPostUseCase;

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

    when(postDAO.findById(postId)).thenReturn(postDataModel);
    when(postDomainMapper.toDomain(postDataModel)).thenReturn(post);

    Post result = findByIDPostUseCase.execute(postId);
    verify(postDAO, times(1)).findById(postId);
    verify(postDomainMapper, times(1)).toDomain(postDataModel);
    assertEquals(postDataModel.getId(), result.getId());
    assertEquals(postDataModel.getContent(), result.getContent());
  }

}