package com.ly.penpixel.infra.entities.authors;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AuthorDataMapperImpl.class })
class AuthorDataMapperTest {

  @Autowired
  private AuthorDataMapper authorDataMapper;

  @Test
  void givenAuthorDomainToAuthorData_whenMaps_thenCorrect() {
    Author author = AuthorUtils.generateAuthor(null);

    AuthorDataModel authorDataModel = authorDataMapper.toModel(author);

    assertEquals(author.getId(), authorDataModel.getId());
    assertEquals(author.getName(), authorDataModel.getName());
    assertEquals(author.getEmail(), authorDataModel.getEmail());
  }
}