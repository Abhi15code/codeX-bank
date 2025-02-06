package com.codeX.codex_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.CreditDebitRequest;
import com.codeX.codex_bank.dto.EnquiryRequest;
import com.codeX.codex_bank.dto.LoginDto;
import com.codeX.codex_bank.dto.TransferRequest;
import com.codeX.codex_bank.dto.UserRequest;
import com.codeX.codex_bank.entity.User;
import com.codeX.codex_bank.service.UserService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/user")
@Tag(name="User Account Management API's")
public class UserController {
    
    @Autowired
    private UserService userService;



    @Operation(
        summary = "Creating a new Account",
        description = "creating a new user account and assigning a Account Number"
        
    )
    @ApiResponse(
        responseCode = "201"
        ,description = "Http status 201 CREATED"

    )

    @PostMapping
    public BankResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
    @Operation(
        summary = "Balance Enquriy",
        description = "given a Account Number, check how much mone Account have"
        
    )
    @ApiResponse(
        responseCode = "200"
        ,description = "Http status 200 SUCCESS"

    )

    @PostMapping("/login")
    public BankResponse login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);

    }

    @GetMapping("/{name}")

    public ResponseEntity<BankResponse> getuserbyname(@PathVariable String name){
            return new ResponseEntity<>(userService.getUserByAccNo(name),HttpStatus.OK);

           
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
