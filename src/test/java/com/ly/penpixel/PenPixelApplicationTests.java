package com.ly.penpixel;

import com.ly.penpixel.domains.authors.AuthorDomainMapperImpl;
import com.ly.penpixel.domains.posts.PostDomainMapper;
import com.ly.penpixel.infra.entities.authors.AuthorDataMapperImpl;
import com.ly.penpixel.infra.entities.posts.PostDataMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { AuthorDomainMapperImpl.class, AuthorDataMapperImpl.class, PostDomainMapper.class, PostDataMapper.class })
@SpringBootTest
class PenPixelApplicationTests {

  @Test
  void contextLoads() {
  }

}
