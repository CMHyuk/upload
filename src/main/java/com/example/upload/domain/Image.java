package com.example.upload.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class Image {

    @Id
    @GeneratedValue
    @Column(name = "image_id")
    private Long id;

    private String uploadName;
    private String storeName;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Image(String uploadName, String storeName, Board board) {
        this.uploadName = uploadName;
        this.storeName = storeName;
        this.board = board;
    }
}
