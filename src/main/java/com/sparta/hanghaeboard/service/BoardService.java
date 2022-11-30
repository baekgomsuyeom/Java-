package com.sparta.hanghaeboard.service;

import com.sparta.hanghaeboard.dto.BoardRequestDto;
import com.sparta.hanghaeboard.dto.BoardResponseDto;
import com.sparta.hanghaeboard.entity.Board;
import com.sparta.hanghaeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }


    @Transactional(readOnly = true)
    public List<BoardResponseDto> getListBoards() {
        List<Board> boardList =  boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();

        for (Board board : boardList) {
            boardResponseDto.add(new BoardResponseDto(board));
        }
        return boardResponseDto;
    }


    @Transactional(readOnly = true)
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new BoardResponseDto(board);
    }


    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (requestDto.getPassword().equals(board.getPassword())) {
            board.update(requestDto);
            return board.getId();
        } else {
            return board.getId();
        }
    }


    @Transactional
    public Map<String, Object> deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Map<String, Object> response = new HashMap<>();
        if (requestDto.getPassword().equals(board.getPassword())) {
            boardRepository.deleteById(id);
            response.put("success",true);
            return response;
        } else {
            response.put("success", false);
            return response;
        }
    }

}