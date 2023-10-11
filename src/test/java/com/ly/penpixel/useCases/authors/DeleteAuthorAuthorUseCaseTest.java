package com.ly.penpixel.useCases.authors;

import com.ly.penpixel.repositories.authors.AuthorDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteAuthorAuthorUseCaseTest {

  @Mock
  private AuthorDAO authorDAO;

  @InjectMocks
  private DeleteAuthorUseCase deleteAuthorUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute() {
    UUID id = UUID.randomUUID();
    doNothing().when(authorDAO).delete(id);
    deleteAuthorUseCase.execute(id);
    verify(authorDAO, times(1)).delete(id);
  }
}