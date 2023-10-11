package com.ly.penpixel.infra.entities.authors;

import com.ly.penpixel.domains.authors.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDataMapper {
  AuthorDataModel toModel(Author author);
}
