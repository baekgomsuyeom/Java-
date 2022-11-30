
package com.sparta.hanghaeboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaeboard.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor

public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    //선택한 게시글 수정(변경)
    public void update(BoardRequestDto boardResponseDto) {
        this.title = boardResponseDto.getTitle();
        this.username = boardResponseDto.getUsername();
        this.contents = boardResponseDto.getContents();
        this.password = boardResponseDto.getPassword();
    }
}
