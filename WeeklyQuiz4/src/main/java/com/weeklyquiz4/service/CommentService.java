package com.weeklyquiz4.service;

import com.weeklyquiz4.domain.Article;
import com.weeklyquiz4.domain.Comment;
import com.weeklyquiz4.dto.AddCommentRequest;
import com.weeklyquiz4.dto.ExternalCommentResponse;
import com.weeklyquiz4.dto.UpdateCommentRequest;
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
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public List<AddCommentRequest> callExternalComments() {
        String url = "https://jsonplaceholder.typicode.com/comments";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ExternalCommentResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});

        List<ExternalCommentResponse> externalComments = response.getBody();

        return externalComments.stream()
                .map(dto -> new AddCommentRequest(dto.getPostId(), dto.getBody()))
                .collect(Collectors.toList());
    }

    public Optional<Comment> saveComment(AddCommentRequest request) {
        Optional<Article> optionalArticle = articleRepository.findById(request.getArticleId());
        if (optionalArticle.isEmpty()) {
            return Optional.empty();
        }
        if (commentRepository.existsByBodyAndArticleId(request.getBody(), request.getArticleId())) {
            return Optional.empty();
        }
        Comment comment = new Comment(optionalArticle.get(), request.getBody());
        return Optional.of(commentRepository.save(comment));
    }

    public Comment create(Long articleId, AddCommentRequest request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Comment comment = new Comment(article, request.getBody());
        return commentRepository.save(comment);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
    }

    public Comment update(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));
        comment.updateBody(request.getBody());
        return commentRepository.save(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
