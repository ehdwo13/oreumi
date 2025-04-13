package com.weeklyquiz4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalArticleResponse {
    private Long id;
    private String title;
    private String body;
}
