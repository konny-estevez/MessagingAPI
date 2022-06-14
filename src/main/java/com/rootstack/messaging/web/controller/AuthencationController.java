package com.rootstack.messaging.web.controller;

import com.rootstack.messaging.domain.service.DatabaseUserDetailsService;
import com.rootstack.messaging.web.model.Login;
import com.rootstack.messaging.web.model.Token;
import com.rootstack.messaging.web.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthencationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    DatabaseUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUitl;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Token> LoginUser(@Validated @RequestBody Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
            Token token = jwtUitl.generateToken(userDetails);
            if (token != null) {
                return ResponseEntity.ok(token);
            } else {
                return new ResponseEntity("Username or password are incorrect.", HttpStatus.UNAUTHORIZED);
            }
        }
        catch (BadCredentialsException bcex) {
            return new ResponseEntity("Username or password are incorrect.", HttpStatus.UNAUTHORIZED);
        }
    }
}
