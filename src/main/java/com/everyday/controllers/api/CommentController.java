package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Comment;
import com.everyday.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public ResponseEntity<APIResponse> getCommentList(@RequestParam int boardKey) {
        APIResponse rsp = null;

        List<Comment> commentList = commentService.getCommentList();

        rsp = new APIResponse(true, "success", commentList);
        return ResponseEntity.ok(rsp);
    }

}
