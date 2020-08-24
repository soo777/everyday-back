package com.everyday.hello;

import com.everyday.model.BoardList;
import com.everyday.repository.BoardListRepository;
import com.everyday.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class hello {

    private final BoardListRepository boardListRepository;

    private final BoardService boardService;

    public hello(BoardListRepository boardListRepository, BoardService boardService) {
        this.boardListRepository = boardListRepository;
        this.boardService = boardService;
    }

    @GetMapping("/test")
    public List<BoardList> hello() {

        List<BoardList> a = boardService.getBoardList();

        return a;
    }

    @GetMapping("/test2")
    public BoardList hello1() {

        BoardList a = boardListRepository.findById(1);

        return a;
    }
}
