package com.codeX.codex_bank.service;

import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.UserRequest;
@Service
public interface UserService {

        BankResponse createUser(UserRequest userRequest);


}
