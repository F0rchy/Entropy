package com.F0rchy.Entropy.repo;

import com.F0rchy.Entropy.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    Iterable<News> findAllByOrderByIdDesc();
}
