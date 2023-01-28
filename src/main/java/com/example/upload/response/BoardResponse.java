package com.example.upload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final List<ImageDto> images;

    @Builder

    public BoardResponse(Long id, String title, String content, List<ImageDto> images) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.images = images;
    }
}
