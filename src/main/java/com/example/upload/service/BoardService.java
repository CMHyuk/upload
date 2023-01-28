package com.example.upload.service;

import com.example.upload.domain.Board;
import com.example.upload.repository.BoardRepository;
import com.example.upload.request.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(BoardRequest boardRequest) {
        Board board = Board.builder()
                .title(boardRequest.getTitle())
                .content(boardRequest.getContent())
                .build();

        return boardRepository.save(board);
    }
}
