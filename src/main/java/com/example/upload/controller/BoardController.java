package com.example.upload.controller;

import com.example.upload.domain.Board;
import com.example.upload.domain.Image;
import com.example.upload.request.BoardRequest;
import com.example.upload.response.BoardResponse;
import com.example.upload.response.ImageDto;
import com.example.upload.service.BoardService;
import com.example.upload.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ImageService imageService;

    @PostMapping("/post")
    public BoardResponse post(@ModelAttribute BoardRequest request) throws IOException {
        Board board = boardService.save(request);
        List<Image> images = imageService.storeImages(request.getImages(), board.getId());
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
}
