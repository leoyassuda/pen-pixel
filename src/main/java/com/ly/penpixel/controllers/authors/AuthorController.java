package com.ly.penpixel.controllers.authors;

import com.ly.penpixel.controllers.IController;
import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.useCases.authors.AuthorUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController implements IController<Author> {

  private final AuthorUseCase authorUseCase;

  @Override
  public ResponseEntity<Author> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(authorUseCase.findById(id));
  }

  @Override
  public ResponseEntity<List<Author>> findAll(@RequestParam(required = false, defaultValue = "20") Integer limit,
                                              @RequestParam(required = false, defaultValue = "1") Integer offset) {
    return ResponseEntity.ok().body(authorUseCase.findAll(limit, offset));
  }

  @Override
  public ResponseEntity<Author> create(@Valid @RequestBody Author author) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authorUseCase.create(author));
  }

  @Override
  public ResponseEntity<Author> update(@PathVariable UUID id, @RequestBody Author author) {
    return ResponseEntity.ok(authorUseCase.update(author));
  }

  @Override
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    authorUseCase.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
