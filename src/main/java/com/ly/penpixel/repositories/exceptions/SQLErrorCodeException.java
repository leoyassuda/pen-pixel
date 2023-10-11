package com.ly.penpixel.repositories.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class SQLErrorCodeException extends SQLErrorCodeSQLExceptionTranslator {
  @Override
  protected DataAccessException
  customTranslate(String task, String sql, SQLException sqlException) {
    if (sqlException.getErrorCode() == 23505) {
      return new DuplicateKeyException(
          String.format("Error on %s - Integrity constraint violation.", task), sqlException);
    }
    return null;
  }
}
