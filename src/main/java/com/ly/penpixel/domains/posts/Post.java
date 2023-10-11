package com.ly.penpixel.domains.posts;

import com.ly.penpixel.domains.authors.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {
  @Serial
  private static final long serialVersionUID = -2476208734665091229L;

  private UUID id;
  private String title;
  private String content;
  private Date publishDate;
  private Author author;
}
