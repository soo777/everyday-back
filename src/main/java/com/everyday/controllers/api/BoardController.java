package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Board;
import com.everyday.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController extends AbstractController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/test")
    public ResponseEntity<APIResponse> hello() {
        APIResponse rsp = null;

        List<Board> boardList = boardService.getBoardList();

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/board")
    public ResponseEntity<APIResponse> getBoardList() {
        APIResponse rsp = null;

        List<Board> boardList = boardService.getBoardList();

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/board")
    public ResponseEntity<APIResponse> addBoardList(@RequestBody Board boardParam) {
        APIResponse rsp = null;

//        log.debug("@@@ param - {}", boardParam);

        Board board = new Board();
        board.setBoardName(boardParam.getBoardName());

        boardService.addBoard(board);

        rsp = new APIResponse(true, "add Board success", board);
        return ResponseEntity.ok(rsp);
    }
}
