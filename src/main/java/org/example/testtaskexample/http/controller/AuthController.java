package org.example.testtaskexample.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testtaskexample.dto.JwtResponse;
import org.example.testtaskexample.dto.UserAuthDto;
import org.example.testtaskexample.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody UserAuthDto userAuthDto) {
        JwtResponse jwt = authService.authUser(userAuthDto);
        return ResponseEntity.ok(jwt);
    }
}
