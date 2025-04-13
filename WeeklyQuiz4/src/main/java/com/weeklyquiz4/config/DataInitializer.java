package com.weeklyquiz4.config;

import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.domain.Comment;
import com.weeklyquiz4.dto.AddArticleRequest;
import com.weeklyquiz4.dto.AddCommentRequest;
import com.weeklyquiz4.service.ArticleService;
import com.weeklyquiz4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final ArticleService articleService;
    private final CommentService commentService;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (articleService.count() > 0) {
                log.info("초기 데이터 이미 존재함. initData 실행 스킵");
                return;
            }

            log.info("초기 데이터가 없어 initData 실행");
            List<AddArticleRequest> articles = articleService.call();
            int articleSavedCount = 0;
            for (AddArticleRequest request : articles) {
                Optional<Article> saved = articleService.saveArticle(request);
                if (saved.isPresent()) {
                    articleSavedCount++;
                }
            }
            log.info("Article 저장 완료: {}건", articleSavedCount);
            List<AddCommentRequest> comments = commentService.callExternalComments();
            int commentSavedCount = 0;
            for (AddCommentRequest request : comments) {
                Optional<Comment> saved = commentService.saveComment(request);
                if (saved.isPresent()) {
                    commentSavedCount++;
                }
            }
            log.info("Comment 저장 완료: {}건", commentSavedCount);
        };
    }



}
