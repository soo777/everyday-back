package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.Board;
import com.everyday.model.BoardList;
import com.everyday.model.User;
import com.everyday.services.BoardService;
import com.everyday.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BoardController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<APIResponse> hello() {
        APIResponse rsp = null;

        List<Board> boardList = boardService.getBoardList();

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/board")
    public ResponseEntity<APIResponse> getBoardList(Authentication auth) {
        APIResponse rsp = null;

        String userName = ((UserDetails) auth.getPrincipal()).getUsername();

        logger.debug("userName - {}", userName);

//        List<Board> boardList = boardService.getBoardList();
        List<Board> boardList = boardService.getBoardList(userName);

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/board")
    public ResponseEntity<APIResponse> addBoardList(Authentication auth, @RequestBody Board boardParam) {
        APIResponse rsp = null;

        logger.debug("@@@ param - {}", boardParam);

        String userName = ((UserDetails) auth.getPrincipal()).getUsername();

        User user = userService.getUser(userName);
        if(user == null) {
            rsp = new APIResponse(false, "not found user", null);
            return ResponseEntity.ok(rsp);
        }

        // board insert
        Board board = new Board();
        board.setBoardName(boardParam.getBoardName());
        board.setCreator(user.getUserId());
        board.setHost(user.getUserId());

        boardService.addBoard(board);

        // boardList insert
        BoardList boardList = new BoardList();

        boardList.setBoardKey(board.getBoardKey());
        boardList.setUserKey(user.getUserKey());
        boardList.setUserId(user.getUserId());

        userService.addBoardMember(boardList);

        rsp = new APIResponse(true, "add Board success", board);
        return ResponseEntity.ok(rsp);
    }
}
