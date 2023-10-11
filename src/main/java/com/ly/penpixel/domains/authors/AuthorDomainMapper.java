package com.ly.penpixel.domains.authors;

import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorDomainMapper {
  @Mapping(target = "postList", source = "postDataModelList")
  Author toDomain(AuthorDataModel authorDataModel);

  List<Author> toDomain(List<AuthorDataModel> authorDataModelList);
}
