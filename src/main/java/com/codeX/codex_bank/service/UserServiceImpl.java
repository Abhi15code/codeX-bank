package com.codeX.codex_bank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.AccountInfo;
import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.CreditDebitRequest;
import com.codeX.codex_bank.dto.EmailDetails;
import com.codeX.codex_bank.dto.EnquiryRequest;
import com.codeX.codex_bank.dto.UserRequest;
import com.codeX.codex_bank.entity.User;
import com.codeX.codex_bank.repository.UserRepository;
import com.codeX.codex_bank.utill.AccountUtill;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService EmailService;

    @Override
    public BankResponse createUser(UserRequest userRequest) {
        /*
         * create an account account - saving a new user into to the db
         * chekc if the user has already has a account
         */
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtill.ACCOUNT_EXISTS_MESSAGE).accountInfo(null).build();
        }

        User newuser = User.builder().firstName(userRequest.getFirstName()).lastName(userRequest.getLastName())
                .gender(userRequest.getGender()).address(userRequest.getAddress()).email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber()).accountNumber(AccountUtill.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO).status("ACTIVE").build();

        User savedUser = userRepository.save(newuser);

        // Send Email Alert

        EmailDetails emailDetails = EmailDetails.builder()
                .reciptient(savedUser.getEmail())
                .subject("Account Created Successfully")
                .messageBody("Congratulations your account has been created successfully.\n Your account details : \n"
                        + "Account Name: " + savedUser.getFirstName() + " " + savedUser.getLastName() + "\n"
                        + "Account Number: " + savedUser.getAccountNumber())
                .build();

        EmailService.sendAlert(emailDetails);

        return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_CREATED_CODE)
                .responseMessage(AccountUtill.ACCOUNT_CREATED_MESSAGE).accountInfo(
                        (AccountInfo.builder().accountNumber(savedUser.getAccountNumber())
                                .accountBalance(savedUser.getAccountBalance())
                                .accountName(newuser.getFirstName() + " " + newuser.getLastName()).build()))
                .build();

    }



    // balance Enquiry , name Enquiry, creding, debit, transer

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {
//      check if the account exists
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!accountExists) {
            return BankResponse.builder().responseCode(AccountUtill.USER_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtill.USER_NOT_EXISTS_MESSAGE).accountInfo(null).build();

        }

        User founduser = userRepository.findByAccountNumber(request.getAccountNumber());

        // return the account balance if the account exists

        return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_EXISTS_CODE)
                .responseMessage(AccountUtill.USER_FOUND_CODE)
                .responseMessage(AccountUtill.USER_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(founduser.getAccountBalance())
                        .accountName(founduser.getFirstName() + " " + founduser.getLastName()).build())
                .build();

    }

    @Override
    public String nameEnquiry(EnquiryRequest enqurityRequest) {
        // check if the account exists
        boolean accountExists = userRepository.existsByAccountNumber(enqurityRequest.getAccountNumber());
        if (!accountExists) {
            return AccountUtill.USER_NOT_EXISTS_MESSAGE;
        }

        User founduser = userRepository.findByAccountNumber(enqurityRequest.getAccountNumber());

        return founduser.getFirstName() + " " + founduser.getLastName();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {

        // check if the account exists
        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return BankResponse.builder().responseCode(AccountUtill.USER_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtill.USER_NOT_EXISTS_MESSAGE).accountInfo(null).build();

        }
        
        // get the user

        User userToCredit = userRepository.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));

        userRepository.save(userToCredit);

        return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_CREDITED_CODE)
        .responseMessage(AccountUtill.ACCOUNT_CREDITED_MESSAGE)
        .accountInfo(AccountInfo.builder().
        accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
        .accountNumber(userToCredit.getAccountNumber())
        .accountBalance(userToCredit.getAccountBalance()).build()).

        
        build();

        

    }



    // Debting balance from a account

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {

        // checking if the account exists

        boolean accountExists = userRepository.existsByAccountNumber(request.getAccountNumber());

        if (!accountExists) {
            return BankResponse.builder().responseCode(AccountUtill.USER_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtill.USER_NOT_EXISTS_MESSAGE).accountInfo(null).build();

        }

        // check you want to withdraw more than the account balance

        User userToDebit  = userRepository.findByAccountNumber(request.getAccountNumber());

        // int avalibleBalance = Integer.parseInt(userToDebit.getAccountBalance().toString());
        // int  debitBalalnce = Integer.parseInt(request.getAmount().toString());
        // we can compare with the above code but we can use the below code
        // if(avalibleBalance < debitBalalnce)
        
        if(userToDebit.getAccountBalance() .compareTo(request.getAmount()) < 0){
            return BankResponse.builder().
            responseCode(AccountUtill.INSUFFICIENT_BALANCE_CODE)
            .responseMessage(AccountUtill.INSUFFICIENT_BALANCE_MESSAGE)
            .accountInfo(null).build();
        }
        else{
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(request.getAmount()));
            userRepository.save(userToDebit);

            return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_DEBITED_CODE)
            .responseMessage(AccountUtill.ACCOUNT_DEBITED_MESSAGE)
            .accountInfo(AccountInfo.builder().
            accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName())
            .accountNumber(userToDebit.getAccountNumber())
            .accountBalance(userToDebit.getAccountBalance()).build()).
    
            
            build();
        }


    }






    
    
}
