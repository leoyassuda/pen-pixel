package com.ly.penpixel.repositories.authors;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.repositories.posts.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class AuthorRowMapper implements RowMapper<AuthorDataModel> {

  private final PostDAO postDAO;

  @Override
  public AuthorDataModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    var authorId = rs.getObject("id", java.util.UUID.class);
    return AuthorDataModel.builder()
        .id(authorId)
        .name(rs.getString("name"))
        .email(rs.getString("email"))
        .postDataModelList(postDAO.findAllByAuthor(authorId))
        .build();
  }
}
