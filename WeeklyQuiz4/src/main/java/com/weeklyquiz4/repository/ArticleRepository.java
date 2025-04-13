package com.weeklyquiz4.repository;

import com.weeklyquiz4.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByTitle(String title);
}
