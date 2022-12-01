package com.sparta.hanghaeboard.controller;

import com.sparta.hanghaeboard.dto.BoardRequestDto;
import com.sparta.hanghaeboard.dto.BoardResponseDto;
import com.sparta.hanghaeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {

    //BoardService 와 연결
    private final BoardService boardService;

    //게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    //전체 게시글 목록 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getListBoards() {
        return boardService.getListBoards();
    }

    //선택한 게시글 조회
    @GetMapping("/board/{id}")
    public BoardResponseDto getBoards(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    //선택한 게시글 수정(변경)
    @PutMapping("/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/board/{id}/{password}")
    public BoardResponseDto deleteBoard(@PathVariable Long id, @PathVariable String password) {
        System.out.println("password controller = " + password);
        return boardService.deleteBoard(id, password);
    }
}
