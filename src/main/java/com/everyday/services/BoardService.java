package com.everyday.services;

import com.everyday.model.Board;
import com.everyday.repository.BoardListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardListRepository boardListRepository;

    public List<Board> getBoardList() {
        return boardListRepository.findAll();
    }

    public void addBoard(Board board) {
        boardListRepository.save(board);
    }

    public Board getBoardList1() {
        return boardListRepository.findById(1);
    }
}
