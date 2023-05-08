package com.mgzk.projectwebfluxfunctional.repository;

import com.mgzk.projectwebfluxfunctional.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostRepository extends ReactiveCrudRepository<Post, Long> {

}
