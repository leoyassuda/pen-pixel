package com.ly.penpixel.domains.posts;

import com.ly.penpixel.infra.entities.posts.PostDataModel;
import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PostDomainMapperImpl.class })
class PostDomainMapperTest {

  @Autowired
  private PostDomainMapper postDomainMapper;

  @Test
  void givenPostDataToPostDomain_whenMaps_thenCorrect() {
    PostDataModel postDataModel = PostUtils.generatePostDataModel(null);

    Post post = postDomainMapper.toDomain(postDataModel);

    assertEquals(postDataModel.getId(), post.getId());
    assertEquals(postDataModel.getContent(), post.getContent());
    assertEquals(postDataModel.getPublishDate(), post.getPublishDate());
  }

  @Test
  void giverPostDataList_whenMaps_thenReturnCorrectModelList() {
    final List<PostDataModel> postDataModelList = Arrays.asList(
        PostUtils.generatePostDataModel(null),
        PostUtils.generatePostDataModel(null),
        PostUtils.generatePostDataModel(null)
    );

    var postModelListResult = postDomainMapper.toDomain(postDataModelList);

    assertEquals(3, postModelListResult.size());
    assertEquals(postModelListResult.get(0).getId(), postDataModelList.get(0).getId());
  }
}