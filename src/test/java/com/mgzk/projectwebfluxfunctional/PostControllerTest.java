package com.mgzk.projectwebfluxfunctional;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

import com.mgzk.projectwebfluxfunctional.handler.PostHandler;
import com.mgzk.projectwebfluxfunctional.model.Post;
import com.mgzk.projectwebfluxfunctional.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class PostControllerTest {

  private final WebTestClient client;

  private final PostRepository repository;

  PostControllerTest() {
    this.repository = Mockito.mock(PostRepository.class);
    PostHandler postHandler = new PostHandler(repository);
    RouterFunction<ServerResponse> routerFunction = RouterFunctions
      .route(GET("/posts/{id}"), postHandler::findById)
      .andRoute(GET("/posts"), postHandler::findAll)
      .andRoute(POST("/posts"), postHandler::save);
    this.client = WebTestClient.bindToRouterFunction(routerFunction).build();
  }

  @Test
  void findById_notExists_notFoundStatusReturned() {
    BDDMockito.when(repository.findById(BDDMockito.anyLong())).thenReturn(Mono.empty());

    client.get().uri("/posts/1")
      .exchange()
      .expectStatus().isNotFound();
  }

  @Test
  void findById_exists_okStatusReturned() {
    BDDMockito.when(repository.findById(BDDMockito.anyLong()))
      .thenReturn(Mono.just(new Post(1L, "webflux", "advantages of using webflux")));

    client.get().uri("/posts/1")
      .exchange()
      .expectStatus().isOk()
      .expectBody().json("{\"id\": 1,\"title\": \"webflux\",\"body\": \"advantages of using webflux\"}", false);
  }
}
