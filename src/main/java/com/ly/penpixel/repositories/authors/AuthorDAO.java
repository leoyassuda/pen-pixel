package com.ly.penpixel.repositories.authors;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.repositories.IDao;
import com.ly.penpixel.repositories.posts.PostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorDAO implements IDao<AuthorDataModel> {

  private final JdbcTemplate jdbcTemplate;
  private final PostDAO postDAO;

  private final String SQL_GET_ALL = "select * from authors limit ? offset ?";
  private final String SQL_FIND_AUTHOR = "select * from authors where id = ?";
  private final String SQL_DELETE_AUTHOR = "delete from authors where id = ?";
  private final String SQL_UPDATE_AUTHOR = "update authors set name = ?, email = ? where id = ?";
  private final String SQL_INSERT_AUTHOR = "insert into authors(name, email) values(?,?)";

  @Override
  public AuthorDataModel findById(UUID id) {
    log.info("Getting author by ID: {}", id);
    return jdbcTemplate.queryForObject(SQL_FIND_AUTHOR, new AuthorRowMapper(postDAO), id);
  }

  @Override
  public List<AuthorDataModel> findAll(Integer limit, Integer offset) {
    log.info("Getting all authors");
    return jdbcTemplate.query(SQL_GET_ALL, new AuthorRowMapper(postDAO), limit, offset);
  }

  @Transactional
  @Override
  public UUID create(AuthorDataModel authorDataModel) {
    log.info("Create author: {}", authorDataModel);

    var keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      var preparedStatement = connection
          .prepareStatement(SQL_INSERT_AUTHOR, new String[]{ "id" });
      preparedStatement.setString(1, authorDataModel.getName());
      preparedStatement.setString(2, authorDataModel.getEmail());
      return preparedStatement;
    }, keyHolder);

    return keyHolder.getKeyAs(UUID.class);
  }

  @Transactional
  @Override
  public int update(AuthorDataModel authorDataModel) {
    log.info("Update author: {}", authorDataModel);

    return jdbcTemplate.update(SQL_UPDATE_AUTHOR,
        authorDataModel.getName(),
        authorDataModel.getEmail(),
        authorDataModel.getId());
  }

  @Transactional
  @Override
  public void delete(UUID id) {
    log.info("Delete author: {}", id);
    jdbcTemplate.update(SQL_DELETE_AUTHOR, id);
  }
}
