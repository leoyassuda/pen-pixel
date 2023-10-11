package com.ly.penpixel.infra.entities.posts;

import com.ly.penpixel.domains.posts.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostDataMapper {
  @Mapping(target = "authorDataModel", source = "author")
  PostDataModel toModel(Post post);
}
