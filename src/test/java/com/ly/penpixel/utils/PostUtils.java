package com.ly.penpixel.utils;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.infra.entities.posts.PostDataModel;

import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class PostUtils {
  private static final Random random = new Random();
  private static final String TITLE = "Content created by ";
  private static final String[] contentOptions = { "Lorem ipsum dolor sit amet", "Consectetur adipiscing elit", "Sed do eiusmod tempor incididunt", "Ut labore et dolore magna aliqua" };

  public static Post generatePostDomain(Author author) {
    var id = UUID.randomUUID();
    var content = generateContent();
    var publishDate = generateDate();
    Author authorMock;

    authorMock = Objects.requireNonNullElseGet(author, () -> AuthorUtils.generateAuthor(null));

    return Post.builder()
        .id(id)
        .content(content)
        .publishDate(publishDate)
        .author(authorMock)
        .build();
  }

  public static PostDataModel generatePostDataModel(AuthorDataModel authorDataModel) {
    var id = UUID.randomUUID();
    var content = generateContent();
    var publishDate = generateDate();
    AuthorDataModel authorMock;

    authorMock = Objects.requireNonNullElseGet(authorDataModel, () -> AuthorUtils.generateAuthorDataModel(null));

    return PostDataModel.builder()
        .id(id)
        .title(TITLE + authorMock.getName())
        .content(content)
        .publishDate(publishDate)
        .authorDataModel(authorMock)
        .build();
  }

  private static String generateContent() {

    var randomIndex = random.nextInt(contentOptions.length);
    return contentOptions[randomIndex];
  }

  private static Date generateDate() {
    var currentTimeMillis = System.currentTimeMillis();
    var randomTimeOffset = (long) (random.nextDouble() * 365 * 24 * 60 * 60 * 1000);
    return new Date(currentTimeMillis - randomTimeOffset);
  }
}
