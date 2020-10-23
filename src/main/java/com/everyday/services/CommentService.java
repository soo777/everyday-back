package com.everyday.services;

import com.everyday.model.Comment;
import com.everyday.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentList() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) { commentRepository.save(comment); }
}
