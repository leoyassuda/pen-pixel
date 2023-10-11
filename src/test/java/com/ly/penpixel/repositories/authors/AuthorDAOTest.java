package com.ly.penpixel.repositories.authors;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.utils.AuthorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AuthorDAOTest {

  @Mock
  JdbcTemplate jdbcTemplate;

  @Mock
  DataSource dataSource;

  @Mock
  private KeyHolder keyHolder;

  @InjectMocks
  AuthorDAO authorDAO;


  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void whenFindAll_thenReturnCorrectAuthorsSize() {
    List<AuthorDataModel> authorDataModelList = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      AuthorDataModel authorDataModel = AuthorUtils.generateAuthorDataModel(null);
      authorDataModelList.add(authorDataModel);
    }

    when(jdbcTemplate.query(eq("select * from authors limit ? offset ?"), any(AuthorRowMapper.class), any(Integer.class), any(Integer.class)))
        .thenReturn(authorDataModelList);

    assertEquals(3, authorDAO.findAll(1, 3).size());
  }

  @Test
  void whenFindById_thenReturnCorrectAuthorById() {
    var authorTest = AuthorUtils.generateAuthorDataModel(null);

    when(jdbcTemplate.queryForObject(eq("select * from authors where id = ?"), any(AuthorRowMapper.class),
        any(UUID.class)))
        .thenReturn(authorTest);

    var result = authorDAO.findById(authorTest.getId());

    assertEquals(authorTest.getName(), result.getName());
    assertEquals(authorTest.getId(), result.getId());
  }

  @Test
  void whenCreate_thenReturnValidUUID() {
    var newAuthorTest = AuthorUtils.generateAuthorDataModel(null);

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class)))
        .thenAnswer(invocation -> {
          keyHolder.getKeyList().add(Collections.singletonMap("id", UUID.randomUUID()));
          Object[] args = invocation.getArguments();
          PreparedStatementCreator psc = (PreparedStatementCreator) args[0];
          GeneratedKeyHolder kh = (GeneratedKeyHolder) args[1];
          return 1;
        });

    authorDAO.create(newAuthorTest);
    assertNotNull(keyHolder.getKeyAs(UUID.class));
  }

  @Test
  void whenUpdate_thenReturnValidRowNumber() {
    var authorTest = AuthorUtils.generateAuthorDataModel(null);

    when(jdbcTemplate.update(
        eq("update authors set name = ?, email = ? where id = ?"),
        eq(authorTest.getName()),
        eq(authorTest.getEmail()),
        any(UUID.class)))
        .thenReturn(1);

    assertEquals(1, authorDAO.update(authorTest));
  }

  @Test
  void whenDelete_thenVerifyCallOnce() {
    var id = UUID.randomUUID();
    authorDAO.delete(id);
    verify(jdbcTemplate, times(1)).update("delete from authors where id = ?", id);
  }
}