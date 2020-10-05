package com.everyday.controllers.api;

import com.everyday.controller.AbstractController;
import com.everyday.messages.APIResponse;
import com.everyday.model.User;
import com.everyday.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
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
}
