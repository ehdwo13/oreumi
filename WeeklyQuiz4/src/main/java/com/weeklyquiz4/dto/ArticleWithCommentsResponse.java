package com.weeklyquiz4.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ArticleWithCommentsResponse {

    private Long articleId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private List<CommentDto> comments;

    @Getter
    @AllArgsConstructor
    public static class CommentDto {
        private Long commentId;
        private Long articleId;
        private String body;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
    }

    public static ArticleWithCommentsResponse from(Article article, List<Comment> comments) {
        return new ArticleWithCommentsResponse(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                comments.stream()
                        .map(c -> new CommentDto(c.getId(), article.getId(), c.getBody(), c.getCreatedAt()))
                        .collect(Collectors.toList())
        );
    }
}

