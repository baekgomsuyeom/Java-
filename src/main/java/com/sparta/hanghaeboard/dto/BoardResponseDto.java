package com.sparta.hanghaeboard.dto;

import com.sparta.hanghaeboard.entity.Board;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter


public class BoardResponseDto {
    private String title;
    private String username;
    private String contents;

    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private Long id;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.username = board.getUsername();
        this.contents = board.getContents();

        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.id = board.getId();
    }
}



