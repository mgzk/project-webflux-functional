package com.mgzk.projectwebfluxfunctional.handler;

import com.mgzk.projectwebfluxfunctional.model.Post;
import com.mgzk.projectwebfluxfunctional.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostHandler {
  private final PostRepository repository;

  public Mono<ServerResponse> findById(ServerRequest request) {
    Mono<Post> postMono = repository.findById(Long.valueOf(request.pathVariable("id")));

    return postMono
      .flatMap(post -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(post)))
      .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    Mono<Post> postMono = request.bodyToMono(Post.class);

    return postMono.flatMap(post ->
      ServerResponse.status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(repository.save(post), Post.class));
  }

  public Mono<ServerResponse> findAll(ServerRequest request) {
    Flux<Post> postFlux = repository.findAll();

    return ServerResponse.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(postFlux, Post.class);
  }
}
