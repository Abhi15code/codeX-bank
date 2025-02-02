package com.codeX.codex_bank.service;

import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.CreditDebitRequest;
import com.codeX.codex_bank.dto.EnquiryRequest;
import com.codeX.codex_bank.dto.LoginDto;
import com.codeX.codex_bank.dto.TransferRequest;
import com.codeX.codex_bank.dto.UserRequest;
@Service
public interface UserService {

        BankResponse createUser(UserRequest userRequest);
        BankResponse balanceEnquiry(EnquiryRequest enqurityRequest);
        String nameEnquiry(EnquiryRequest enqurityRequest);
        BankResponse creditAccount(CreditDebitRequest creaditDebitRequest);
        BankResponse debitAccount(CreditDebitRequest creaditDebitRequest);
        BankResponse transfer(TransferRequest transferRequest); 
        BankResponse login(LoginDto loginDto);

}

