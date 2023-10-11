package com.ly.penpixel.repositories.posts;

import com.ly.penpixel.infra.entities.posts.PostDataModel;
import com.ly.penpixel.repositories.IDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class PostDAO implements IDao<PostDataModel> {

  private final JdbcTemplate jdbcTemplate;

  private final String SQL_GET_ALL = "select * from posts";
  private final String SQL_FIND_POST = "select * from posts where id = ?";
  private final String SQL_FIND_POST_FROM_AUTHOR = "select * from posts p join authors a on p.author_id = a.id where a.id = ?";
  private final String SQL_DELETE_POST = "delete from posts where id = ?";
  private final String SQL_UPDATE_POST = "update posts set title = ?, content = ?, publish_date = ? where id = ?";
  private final String SQL_INSERT_POST = "insert into posts(title, content, publish_date, author_id) values(?,?,?)";

  @Override
  public PostDataModel findById(UUID id) {
    log.info("Getting post by ID: {}", id);
    return jdbcTemplate.queryForObject(SQL_FIND_POST, new PostRowMapper(), id);
  }

  @Override
  public List<PostDataModel> findAll(Integer limit, Integer offset) {
    log.info("Getting all posts");
    return jdbcTemplate.query(SQL_GET_ALL, new PostRowMapper());
  }

  public List<PostDataModel> findAllByAuthor(UUID authorId) {
    log.info("Getting all posts from author: {}", authorId);
    return jdbcTemplate.query(SQL_FIND_POST_FROM_AUTHOR, new PostRowMapper(), authorId);
  }

  @Transactional
  @Override
  public UUID create(PostDataModel postDataModel) {
    log.info("Create post: {}", postDataModel);

    var keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      var preparedStatement = connection
          .prepareStatement(SQL_INSERT_POST, new String[]{ "id" });
      preparedStatement.setString(1, postDataModel.getTitle());
      preparedStatement.setString(2, postDataModel.getContent());
      preparedStatement.setDate(3, (Date) postDataModel.getPublishDate());
      preparedStatement.setObject(4, postDataModel.getAuthorDataModel());
      return preparedStatement;
    }, keyHolder);

    return keyHolder.getKeyAs(UUID.class);
  }

  @Transactional
  @Override
  public int update(PostDataModel postDataModel) {
    log.info("Update post: {}", postDataModel);

    return jdbcTemplate.update(SQL_UPDATE_POST,
        postDataModel.getTitle(),
        postDataModel.getContent(),
        postDataModel.getPublishDate(),
        postDataModel.getId());
  }

  @Transactional
  @Override
  public void delete(UUID id) {
    log.info("Delete post: {}", id);
    jdbcTemplate.update(SQL_DELETE_POST, id);
  }
}
