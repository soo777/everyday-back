package com.everyday.controllers.api;

import com.everyday.config.JwtTokenUtil;
import com.everyday.messages.APIResponse;
import com.everyday.model.JwtRequest;
import com.everyday.model.JwtResponse;
import com.everyday.model.User;
import com.everyday.services.JwtUserDetailsService;
import com.everyday.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/authenticate")
    public ResponseEntity<APIResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        APIResponse rsp = null;

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        rsp = new APIResponse(true, "success", token);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/signIn")
    public ResponseEntity<APIResponse> signIn(@RequestBody User userParam) {
        APIResponse rsp = null;

        // userId check
        User userCheck = userService.getUser(userParam.getUserId());
        if(userCheck != null) {
            rsp = new APIResponse(false, "signIn fail", "userId is already existed");
            return ResponseEntity.ok(rsp);
        }

        String encryptedPassword = BCrypt.hashpw(userParam.getPassword(), BCrypt.gensalt());

        User user = new User();
        user.setUserId(userParam.getUserId());
        user.setPassword(encryptedPassword);
        user.setName(userParam.getName());

        userService.saveUser(user);

        rsp = new APIResponse(true, "signIn success", null);
        return ResponseEntity.ok(rsp);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
