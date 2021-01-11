package com.everyday.services;

import com.everyday.model.BoardList;
import com.everyday.model.User;
import com.everyday.repository.BoardListRepository;
import com.everyday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardListRepository boardListRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<BoardList> getMemberList(int boardKey) {
        return boardListRepository.findByBoardKey(boardKey);
    }
}
