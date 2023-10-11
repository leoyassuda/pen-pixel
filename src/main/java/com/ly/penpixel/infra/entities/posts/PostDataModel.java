package com.ly.penpixel.infra.entities.posts;


import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDataModel {
  //  @Serial
  //  private static final long serialVersionUID = -332243780504300470L;
  private UUID id;
  private String title;
  private String content;
  private Date publishDate;
  private AuthorDataModel authorDataModel;
}
