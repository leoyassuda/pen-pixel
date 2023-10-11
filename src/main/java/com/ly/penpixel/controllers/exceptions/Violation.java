package com.ly.penpixel.controllers.exceptions;

import lombok.Data;

@Data
public class Violation {

  private final String fieldName;

  private final String message;

  public Violation(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }
}
