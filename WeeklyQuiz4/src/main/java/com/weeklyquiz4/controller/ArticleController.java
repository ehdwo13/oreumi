package com.weeklyquiz4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.dto.AddArticleRequest;
import com.weeklyquiz4.dto.ArticleSummaryResponse;
import com.weeklyquiz4.dto.ArticleWithCommentsResponse;
import com.weeklyquiz4.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    @Autowired
    private ObjectMapper mapper;

    //api -> db article 저장
    //초기 1회
    @GetMapping("/article")
    public void callArticleApi() {
        List<AddArticleRequest> articles = articleService.call();
        for (AddArticleRequest request : articles) {
            articleService.saveArticle(request);
        }
    }

    // 전체 게시글 조회 (프론트에서 페이징 처리)
    @GetMapping("/api/articles/all")
    public ResponseEntity<List<ArticleSummaryResponse>> getAllArticles() {
        List<Article> articles = articleService.findAll();
        List<ArticleSummaryResponse> response = articles.stream()
                .map(ArticleSummaryResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }


    // 5. 댓글 포함 게시글 상세 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<ArticleWithCommentsResponse> getArticleWithComments(@PathVariable Long articleId) throws JsonProcessingException {
        ArticleWithCommentsResponse response = articleService.getArticleWithComments(articleId);
        HttpStatus status = HttpStatus.OK;
        log.info("댓글 포함 게시글 조회 응답코드: {}, Response:\n{}", status.value(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return ResponseEntity.status(status).body(response);
    }

}
