package com.everyday.services;

import com.everyday.model.Board;
import com.everyday.model.BoardList;
import com.everyday.repository.BoardListRepository;
import com.everyday.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardListRepository boardListRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public List<Board> getBoardList(String userId) {
        return boardRepository.findByUserId(userId);
    }

    public void addBoard(Board board) {
        boardRepository.save(board);
    }

    public Board getBoardList1() {
        return boardRepository.findById(1);
    }
}
