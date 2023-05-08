package com.mgzk.projectwebfluxfunctional.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
  @Id
  private Long id;

  @NonNull
  private String title;

  @NonNull
  private String body;
}
