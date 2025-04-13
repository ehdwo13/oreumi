package com.weeklyquiz4.service;

import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.domain.Comment;
import com.weeklyquiz4.dto.AddArticleRequest;
import com.weeklyquiz4.dto.ArticleWithCommentsResponse;
import com.weeklyquiz4.dto.ExternalArticleResponse;
import com.weeklyquiz4.repository.ArticleRepository;
import com.weeklyquiz4.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<AddArticleRequest> call() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<ExternalArticleResponse>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        List<ExternalArticleResponse> externalArticles = response.getBody();
        return externalArticles.stream()
                .map(dto -> new AddArticleRequest(dto.getTitle(), dto.getBody()))
                .collect(Collectors.toList());
    }


    public Optional<Article> saveArticle(AddArticleRequest request) {
        if (articleRepository.existsByTitle(request.getTitle())) {
            return Optional.empty();
        }

        Article article = new Article(request.getTitle(), request.getContent());
        return Optional.of(articleRepository.save(article));
    }


    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    public ArticleWithCommentsResponse getArticleWithComments(Long articleId) {
        Article article = findById(articleId);
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return ArticleWithCommentsResponse.from(article, comments);
    }

    public long count() {
        return articleRepository.count();
    }
}
