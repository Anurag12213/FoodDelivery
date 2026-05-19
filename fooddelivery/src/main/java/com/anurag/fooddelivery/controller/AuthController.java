package com.anurag.fooddelivery.controller;

import com.anurag.fooddelivery.dto.LoginRequest;
import com.anurag.fooddelivery.entity.User;
import com.anurag.fooddelivery.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService service;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody User user) {

        Object response =
                service.register(user);

        if (response instanceof String) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
        @PostMapping("/login")
        public String login(@RequestBody LoginRequest request){
        return service.login(request);
    }

}
