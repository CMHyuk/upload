package com.example.upload.response;

import com.example.upload.domain.Board;
import com.example.upload.domain.Image;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class BoardResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final List<ImageDto> images;

    public static BoardResponse toEntity(Board board, List<Image> images) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .images(images.stream()
                        .map(i -> ImageDto.builder()
                                .uploadName(i.getUploadName())
                                .storeName(i.getStoreName())
                                .build())
                        .collect(toList()))
                .build();
    }

    @Builder
    public BoardResponse(Long id, String title, String content, List<ImageDto> images) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.images = images;
    }
}
