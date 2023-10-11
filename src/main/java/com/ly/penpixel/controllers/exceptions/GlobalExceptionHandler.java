package com.ly.penpixel.controllers.exceptions;

import com.ly.penpixel.useCases.exceptions.ResourceCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(ResourceCreationException.class)
  public ResponseEntity<ErrorResponse> handleException(ResourceCreationException cause, Locale locale) {
    return new ResponseEntity<>(
        new ErrorResponse(cause.getMessage(), "ResourceNotFound"), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<ErrorResponse> handleException(InvalidOperationException cause) {
    return new ResponseEntity<>(
        new ErrorResponse(cause.getMessage(), "InvalidOperation"), HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException cause, Locale locale) {
    String errorMessage = messageSource.getMessage("error.jdbc.notFound", null, locale);
    return new ResponseEntity<>(
        new ErrorResponse(errorMessage, "DataAccessError"), HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<ErrorResponse> handleException(DuplicateKeyException cause, Locale locale) {
    String errorMessage = messageSource.getMessage("error.jdbc.duplicate-key", null, locale);
    return new ResponseEntity<>(
        new ErrorResponse(errorMessage, "DataAccessError"), HttpStatus.PRECONDITION_FAILED);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
    String customMessage = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField().toUpperCase() + ": " + fieldError.getDefaultMessage())
        .collect(Collectors.joining(", "));

    return ResponseEntity.badRequest().body(new ErrorResponse(customMessage, "invalidParameter"));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleException(RuntimeException cause, Locale locale) {
    log.error("========== ERROR ", cause);
    return new ResponseEntity<>(
        new ErrorResponse(cause.getMessage(), "RuntimeException"),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
