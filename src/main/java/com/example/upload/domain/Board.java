package com.example.upload.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = {PERSIST, REMOVE})
    List<Image> images = new ArrayList<>();

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
