package com.ly.penpixel.domains.authors;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AuthorDomainMapperImpl.class })
class AuthorDomainMapperTest {

  @Autowired
  private AuthorDomainMapper authorDomainMapper;

  @Test
  void givenAuthorDataToAuthorDomain_whenMaps_thenCorrect() {
    AuthorDataModel authorDataModel = AuthorUtils.generateAuthorDataModel(null);

    Author author = authorDomainMapper.toDomain(authorDataModel);

    assertEquals(authorDataModel.getId(), author.getId());
    assertEquals(authorDataModel.getName(), author.getName());
    assertEquals(authorDataModel.getEmail(), author.getEmail());
  }

  @Test
  void giverAuthorDataList_whenMaps_thenReturnCorrectModelList() {
    final List<AuthorDataModel> authorDataModelList = Arrays.asList(
        AuthorUtils.generateAuthorDataModel(null),
        AuthorUtils.generateAuthorDataModel(null),
        AuthorUtils.generateAuthorDataModel(null)
    );

    var authorModelListResult = authorDomainMapper.toDomain(authorDataModelList);

    assertEquals(3, authorModelListResult.size());
    assertEquals(authorModelListResult.get(0).getId(), authorDataModelList.get(0).getId());
  }
}