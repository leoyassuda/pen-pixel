package com.ly.penpixel.domains.posts;

import com.ly.penpixel.infra.entities.posts.PostDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostDomainMapper {
  @Mapping(target = "author", source = "authorDataModel")
  Post toDomain(PostDataModel postDataModel);

  List<Post> toDomain(List<PostDataModel> postDataModelList);
}
