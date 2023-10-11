package com.ly.penpixel.repositories.posts;

import com.ly.penpixel.infra.entities.posts.PostDataModel;
import com.ly.penpixel.utils.PostUtils;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PostDAOTest {

  @Mock
  JdbcTemplate jdbcTemplate;

  @Mock
  DataSource dataSource;

  @Mock
  private KeyHolder keyHolder;

  @InjectMocks
  PostDAO postDAO;


  @BeforeEach
  void setup()  {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void whenFindAll_thenReturnCorrectPostsSize() {
    List<PostDataModel> postDataModelList = new ArrayList<>();

    for (int i = 1; i <= 3; i++) {
      PostDataModel postDataModel = PostUtils.generatePostDataModel(null);
      postDataModelList.add(postDataModel);
    }

    when(jdbcTemplate.query(eq("select * from posts"), Mockito.any(PostRowMapper.class)))
        .thenReturn(postDataModelList);

    assertEquals(3, postDAO.findAll(1, 3).size());
  }

  @Test
  void whenFindById_thenReturnCorrectPostById() {
    var postTest = PostUtils.generatePostDataModel(null);

    when(jdbcTemplate.queryForObject(eq("select * from posts where id = ?"), Mockito.any(PostRowMapper.class),
        Mockito.any(UUID.class)))
        .thenReturn(postTest);

    assertNotNull(postDAO.findById(postTest.getId()).getContent());
  }

  @Test
  void whenCreate_thenReturnValidUUID() {
    var postTest = PostUtils.generatePostDataModel(null);

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

    when(jdbcTemplate.update(any(), any(GeneratedKeyHolder.class)))
        .thenAnswer(invocation -> {
          keyHolder.getKeyList().add(Collections.singletonMap("id", UUID.randomUUID()));
          Object[] args = invocation.getArguments();
          PreparedStatementCreator psc = (PreparedStatementCreator) args[0];
          GeneratedKeyHolder kh = (GeneratedKeyHolder) args[1];
          return 1;
        });

    postDAO.create(postTest);
    assertNotNull(keyHolder.getKeyAs(UUID.class));
  }

  @Test
  void whenUpdate_thenReturnValidRowNumber() {
    var postTest = PostUtils.generatePostDataModel(null);

    when(jdbcTemplate.update(
        eq("update posts set title = ?, content = ?, publish_date = ? where id = ?"),
        eq(postTest.getTitle()),
        eq(postTest.getContent()),
        eq(postTest.getPublishDate()),
        Mockito.any(UUID.class)))
        .thenReturn(1);

    assertEquals(1, postDAO.update(postTest));
  }

  @Test
  void whenDelete_thenVerifyCallOnce() {
    var id = UUID.randomUUID();
    postDAO.delete(id);
    verify(jdbcTemplate, times(1)).update("delete from posts where id = ?", id);
  }
}