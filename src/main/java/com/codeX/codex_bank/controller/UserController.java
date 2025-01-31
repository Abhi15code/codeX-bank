package com.codeX.codex_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.UserRequest;
import com.codeX.codex_bank.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public BankResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    

    



}
