package com.sparta.hanghaeboard.service;

import com.sparta.hanghaeboard.dto.BoardRequestDto;
import com.sparta.hanghaeboard.dto.BoardResponseDto;
import com.sparta.hanghaeboard.entity.Board;
import com.sparta.hanghaeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


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
    public BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        System.out.println(requestDto.getPassword());
        System.out.println(board.getPassword());


        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);
            return new BoardResponseDto(board);

        } else {
            return new BoardResponseDto("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Transactional
    public BoardResponseDto deleteBoard (Long id, String password) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        System.out.println("board password= " + board.getPassword());
        System.out.println("password = " + password);

        if(board.getPassword().equals(password))
            boardRepository.deleteById(id);

        return new BoardResponseDto("게시글을 삭제했습니다.", HttpStatus.OK.value());
        }
    }

