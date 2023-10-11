package com.ly.penpixel.controllers.exceptions;

public class InvalidOperationException extends RuntimeException {
  public InvalidOperationException(String message) {
    super(message);
  }
}
