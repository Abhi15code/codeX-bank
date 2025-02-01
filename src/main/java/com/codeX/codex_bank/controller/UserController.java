package com.codeX.codex_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.CreditDebitRequest;
import com.codeX.codex_bank.dto.EnquiryRequest;
import com.codeX.codex_bank.dto.TransferRequest;
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

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
            return userService.balanceEnquiry(enquiryRequest);
    }

    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
        return userService.nameEnquiry(enquiryRequest);
    }

    @PostMapping("/credit")
   public BankResponse creditAccount(@RequestBody CreditDebitRequest request){
    
    return userService.creditAccount(request);
   }

   @PostMapping("/debit")
   public BankResponse debitAccount(@RequestBody CreditDebitRequest request){
    
    return userService.debitAccount(request);

   }

   @PostMapping("/transfer")
   public BankResponse transfer(@RequestBody TransferRequest request){
    return userService.transfer(request);
   }

    



}
