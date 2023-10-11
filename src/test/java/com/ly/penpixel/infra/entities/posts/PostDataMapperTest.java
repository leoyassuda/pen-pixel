package com.ly.penpixel.infra.entities.posts;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.utils.AuthorUtils;
import com.ly.penpixel.utils.PostUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PostDataMapperImpl.class })
class PostDataMapperTest {
  @Autowired
  private PostDataMapper postDataMapper;

  @Test
  void givenAuthorDomainToAuthorData_whenMaps_thenCorrect() {
    Post post = PostUtils.generatePostDomain(null);

    PostDataModel postDataModel = postDataMapper.toModel(post);

    assertEquals(post.getId(), postDataModel.getId());
    assertEquals(post.getContent(), postDataModel.getContent());
    assertEquals(post.getPublishDate(), postDataModel.getPublishDate());
  }
}