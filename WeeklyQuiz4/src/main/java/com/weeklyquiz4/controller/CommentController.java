package com.weeklyquiz4.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weeklyquiz4.domain.Comment;
import com.weeklyquiz4.dto.AddCommentRequest;
import com.weeklyquiz4.dto.CommentResponse;
import com.weeklyquiz4.dto.UpdateCommentRequest;
import com.weeklyquiz4.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @Autowired
    private ObjectMapper mapper;


    // 외부 API -> DB 저장
    //초기 1회
    @GetMapping("/comment")
    public void fetchAndSaveComments() {
        List<AddCommentRequest> comments = commentService.callExternalComments();
        for (AddCommentRequest request : comments) {
            commentService.saveComment(request);
        }
    }

    // 1. 댓글 생성
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long articleId,
            @RequestBody AddCommentRequest request
    ) throws JsonProcessingException {
        Comment comment = commentService.create(articleId, request);
        CommentResponse response = new CommentResponse(comment);
        HttpStatus status = HttpStatus.CREATED;
        log.info("댓글 생성 응답코드: {}, Response:\n{}", status.value(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return ResponseEntity.status(status).body(response);
    }

    // 2. 댓글 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long commentId) throws JsonProcessingException {
        Comment comment = commentService.getComment(commentId);
        CommentResponse response = new CommentResponse(comment);
        HttpStatus status = HttpStatus.OK;
        log.info("댓글 조회 응답코드: {}, Response:\n{}", status.value(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return ResponseEntity.status(status).body(response);
    }

    // 3. 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody UpdateCommentRequest request
    ) throws JsonProcessingException {
        Comment updated = commentService.update(commentId, request);
        CommentResponse response = new CommentResponse(updated);
        HttpStatus status = HttpStatus.OK;
        log.info("댓글 수정 응답코드: {}, Response:\n{}", status.value(),
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        return ResponseEntity.status(status).body(response);
    }

    // 4. 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
        HttpStatus status = HttpStatus.OK;
        log.info("댓글 삭제 응답코드: {}", status.value());
        return ResponseEntity.status(status).build();
    }
}
