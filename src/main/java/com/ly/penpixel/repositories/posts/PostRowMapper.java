package com.ly.penpixel.repositories.posts;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.infra.entities.posts.PostDataModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PostRowMapper implements RowMapper<PostDataModel> {
  @Override
  public PostDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    return PostDataModel.builder()
        .id(rs.getObject("id", UUID.class))
        .authorDataModel(
            AuthorDataModel.builder()
                .id(rs.getObject("author_id", UUID.class))
                .build()
        )
        .content(rs.getString("content"))
        .publishDate(rs.getDate("publish_date"))
        .build();
  }
}
