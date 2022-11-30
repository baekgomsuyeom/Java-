package com.sparta.hanghaeboard.dto;

import lombok.Getter;

import lombok.Setter;


@Getter
@Setter

public class BoardRequestDto {
    private String title;
    private String username;
    private String contents;
    private String password;
}

