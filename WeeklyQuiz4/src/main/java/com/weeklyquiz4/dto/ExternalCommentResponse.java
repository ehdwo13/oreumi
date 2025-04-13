package com.weeklyquiz4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalCommentResponse {
    private Long postId;
    private String body;
}
