package com.ly.penpixel.repositories;

import java.util.List;
import java.util.UUID;

public interface IDao<T> {
  T findById(UUID id);

  List<T> findAll(Integer limit, Integer offset);

  UUID create(T t);

  int update(T t);

  void delete(UUID id);
}
