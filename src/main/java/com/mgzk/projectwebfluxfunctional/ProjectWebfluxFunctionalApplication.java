package com.mgzk.projectwebfluxfunctional;

import com.mgzk.projectwebfluxfunctional.handler.PostHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ProjectWebfluxFunctionalApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProjectWebfluxFunctionalApplication.class, args);
  }

  @Bean
  RouterFunction<ServerResponse> routes(PostHandler postHandler) {
    return route(GET("/posts/{id}"), postHandler::findById)
      .andRoute(GET("/posts"), postHandler::findAll)
      .andRoute(POST("/posts"), postHandler::save);
  }
}
