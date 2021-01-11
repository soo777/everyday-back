package com.everyday.services;

import com.everyday.model.Board;
import com.everyday.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public void addBoard(Board board) {
        boardRepository.save(board);
    }

    public Board getBoardList1() {
        return boardRepository.findById(1);
    }
}
