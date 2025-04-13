package com.weeklyquiz4.repository;

import com.weeklyquiz4.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId);

    boolean existsByBodyAndArticleId(String body, Long articleId);
}
