package com.ly.penpixel.utils;

import com.ly.penpixel.domains.authors.Author;
import com.ly.penpixel.domains.posts.Post;
import com.ly.penpixel.infra.entities.authors.AuthorDataModel;
import com.ly.penpixel.infra.entities.posts.PostDataModel;

import java.util.*;

public class AuthorUtils {

  private static final String[] firstName = { "João", "Maria", "José", "Ana", "Pedro", "Sandra", "Carlos", "Marta" };
  private static final String[] lastName = { "Silva", "Santos", "Pereira", "Oliveira", "Costa", "Ferreira", "Rodrigues", "Gomes" };
  private static final String EMAIL_DOMAIN = "@mail.com";

  public static Author generateAuthor(UUID uuid) {
    var authorMocked = Author.builder()
        .name(generateName())
        .email(generateEmail())
        .postList(Collections.singletonList(
            Post.builder()
                .id(UUID.randomUUID())
                .title("Some title")
                .content("Some content")
                .publishDate(new Date())
                .build()
        ))
        .build();
    authorMocked.setId(Objects.requireNonNullElseGet(uuid, UUID::randomUUID));
    return authorMocked;
  }

  public static AuthorDataModel generateAuthorDataModel(UUID uuid) {
    var authorMocked = AuthorDataModel.builder()
        .name(generateName())
        .email(generateEmail())
        .postDataModelList(Collections.singletonList(
            PostDataModel.builder()
                .id(UUID.randomUUID())
                .title("Some title")
                .content("Some content")
                .publishDate(new Date())
                .build()
        ))
        .build();
    authorMocked.setId(Objects.requireNonNullElseGet(uuid, UUID::randomUUID));
    return authorMocked;
  }

  private static String generateName() {
    Random random = new Random();
    return firstName[random.nextInt(firstName.length)] + " " +
        lastName[random.nextInt(lastName.length)];
  }

  private static String generateEmail() {
    String randomName = generateName();
    String[] partesNome = randomName.split(" ");
    String primeiroNome = partesNome[0].toLowerCase();
    return primeiroNome + EMAIL_DOMAIN;
  }
}
