package com.F0rchy.Entropy.repo;

import com.F0rchy.Entropy.models.Articles;

import org.springframework.data.repository.CrudRepository;

public interface ArticlesRepository extends CrudRepository<Articles, Long> {
    Iterable<Articles> findAllByOrderByIdDesc();
}
