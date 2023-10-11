package com.ly.penpixel.useCases;

import java.util.List;
import java.util.UUID;

public interface IUseCase<T> {

  T findById(UUID id);

  List<T> findAll(Integer limit, Integer offset);

  T create(T t);

  T update(T t);

  void delete(UUID id);
}
