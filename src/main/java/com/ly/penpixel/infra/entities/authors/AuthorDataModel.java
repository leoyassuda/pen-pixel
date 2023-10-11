package com.ly.penpixel.infra.entities.authors;


import com.ly.penpixel.infra.entities.posts.PostDataModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDataModel {
  //  @Serial
  //  private static final long serialVersionUID = -8604040219075651261L;
  private UUID id;
  private String name;
  private String email;
  private List<PostDataModel> postDataModelList;
}
