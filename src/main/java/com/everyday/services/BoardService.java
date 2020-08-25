package com.everyday.services;

import com.everyday.model.BoardList;
import com.everyday.repository.BoardListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardListRepository boardListRepository;

    public List<BoardList> getBoardList() {
        return boardListRepository.findAll();
    }

    public BoardList getBoardList1() {
        return boardListRepository.findById(1);
    }
}
