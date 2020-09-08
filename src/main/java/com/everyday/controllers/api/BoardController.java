package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.BoardList;
import com.everyday.repository.BoardListRepository;
import com.everyday.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController extends AbstractController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/test")
    public ResponseEntity<APIResponse> hello() {
        APIResponse rsp = null;

        List<BoardList> boardList = boardService.getBoardList();

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/board")
    public ResponseEntity<APIResponse> board() {
        APIResponse rsp = null;

        List<BoardList> boardList = boardService.getBoardList();

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }
}
