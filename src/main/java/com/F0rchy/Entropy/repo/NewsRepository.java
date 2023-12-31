package com.F0rchy.Entropy.repo;

import com.F0rchy.Entropy.models.Articles;
import com.F0rchy.Entropy.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {
    Iterable<News> findAllByOrderByIdDesc();

    Iterable<News> findByTitleContainingIgnoreCaseOrderByIdDesc(String title);
}
