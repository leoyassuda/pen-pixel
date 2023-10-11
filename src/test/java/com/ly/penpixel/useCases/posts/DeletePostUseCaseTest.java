package com.ly.penpixel.useCases.posts;

import com.ly.penpixel.repositories.posts.PostDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeletePostUseCaseTest {
  @Mock
  private PostDAO postDAO;

  @InjectMocks
  private DeletePostUseCase deletePostUseCase;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void execute() {
    UUID id = UUID.randomUUID();
    doNothing().when(postDAO).delete(id);
    deletePostUseCase.execute(id);
    verify(postDAO, times(1)).delete(id);
  }
}