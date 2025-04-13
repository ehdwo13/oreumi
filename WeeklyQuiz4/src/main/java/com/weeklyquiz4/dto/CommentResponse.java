package com.weeklyquiz4.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private ArticleInfo article;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.articleId = comment.getArticle().getId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        this.article = new ArticleInfo(comment.getArticle());
    }

    @Getter
    public static class ArticleInfo {
        private Long articleId;
        private String title;
        private String content;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime updatedAt;

        public ArticleInfo(Article article) {
            this.articleId = article.getId();
            this.title = article.getTitle();
            this.content = article.getContent();
            this.createdAt = article.getCreatedAt();
            this.updatedAt = article.getUpdatedAt();
        }
    }
}
