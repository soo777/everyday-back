package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.BoardList;
import com.everyday.model.User;
import com.everyday.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<APIResponse> getUser(@RequestBody User userParam) {
        APIResponse rsp = null;

        User user = userService.getUser(userParam.getUserId());

        rsp = new APIResponse(true, "success", user);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/user/userDetail")
    public ResponseEntity<APIResponse> getUserDetail(Authentication auth, @RequestParam String userId) {
        APIResponse rsp = null;

        String userName = ((UserDetails) auth.getPrincipal()).getUsername();

        logger.debug("userName - {}", userName);

        User user;

        if (userName.equals(userId)) {
            user = userService.getUser(userId);
        } else {
            rsp = new APIResponse(false, "you don't have permission", null);
            return ResponseEntity.ok(rsp);
        }

        Map map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("name", user.getName());
        map.put("created", user.getCreateDate());

        rsp = new APIResponse(true, "success", map);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/user/board/memberList")
    public ResponseEntity<APIResponse> getMemberList(Authentication auth, @RequestParam int boardKey) {
        APIResponse rsp = null;

        List<BoardList> memberList = userService.getMemberList(boardKey);

        rsp = new APIResponse(true, "success", memberList);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/user/board/memberList")
    public ResponseEntity<APIResponse> addMemberList(Authentication auth, @RequestParam int boardKey, @RequestParam List<String> memberList) {
        APIResponse rsp = null;

        User user;

        for (int i = 0; i < memberList.size(); i++) {
            user = userService.getUser(memberList.get(i));

            BoardList board = new BoardList();

            board.setBoardKey(boardKey);
            board.setUserKey(user.getUserKey());
            board.setUserId(user.getUserId());

            userService.addBoardMember(board);
        }

        List<BoardList> boardList = userService.getMemberList(boardKey);

        rsp = new APIResponse(true, "success", boardList);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/user/member/check")
    public ResponseEntity<APIResponse> getMemberCheck(Authentication auth, @RequestParam int boardKey, @RequestParam String userId) {
        APIResponse rsp = null;

        List<BoardList> boardList = userService.getMemberList(boardKey);

        for (int i = 0; i < boardList.size(); i++) {
            if (boardList.get(i).getUserId().equals(userId)) {
                rsp = new APIResponse(false, "success", null);
                return ResponseEntity.ok(rsp);
            }
        }

        rsp = new APIResponse(true, "success", null);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/user/list")
    public ResponseEntity<APIResponse> getMemberListById(Authentication auth, @RequestParam String userId) {
        APIResponse rsp = null;

        List<User> memberList = userService.getMemberListById(userId);

        rsp = new APIResponse(true, "success", memberList);
        return ResponseEntity.ok(rsp);
    }
}
