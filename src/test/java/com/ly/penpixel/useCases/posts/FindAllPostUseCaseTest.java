package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.infra.entities.posts.PostDataModel;
import com.ly.penpixel.repositories.posts.PostDAO;
import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FindAllPostUseCaseTest {

  @Mock
  private PostDAO postDAO;

  @Mock
  private PostDomainMapper postDomainMapper;

  @InjectMocks
  private FindAllPostUseCase findAllPostsUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenExecute_thenReturnCorrectList() {
    List<PostDataModel> postDataModelList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      PostDataModel postDataModel = PostUtils.generatePostDataModel(null);
      postDataModelList.add(postDataModel);
    }
    for (int i = 1; i <= 3; i++) {
      Post post = PostUtils.generatePostDomain(null);
      postList.add(post);
    }

    when(postDAO.findAll(1,20)).thenReturn(postDataModelList);
    when(postDomainMapper.toDomain(postDataModelList)).thenReturn(postList);

    var resultList = findAllPostsUseCase.execute(1,20);
    verify(postDAO, times(1)).findAll(1,20);
    verify(postDomainMapper, times(1)).toDomain(postDataModelList);
    assertEquals(3, resultList.size());
    assertNotNull(resultList.get(0).getAuthor());
  }
}