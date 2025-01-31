package com.codeX.codex_bank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.AccountInfo;
import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.EmailDetails;
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
        .messageBody("Congratulations your account has been created successfully. Your account number is:\n your account details : \n"+savedUser.getAccountNumber()
        +"\n"+savedUser.getFirstName()+" "+savedUser.getLastName()+"\n"+savedUser.getEmail()+"\n"+savedUser.getPhoneNumber())
        .build();

        EmailService.sendAlert(emailDetails);
        
        return BankResponse.builder().responseCode(AccountUtill.ACCOUNT_CREATED_CODE)
                .responseMessage(AccountUtill.ACCOUNT_CREATED_MESSAGE).
                accountInfo(
                    (AccountInfo.builder().
                        accountNumber(savedUser.getAccountNumber())
                                .accountBalance(savedUser.getAccountBalance()).
                                accountName(newuser.getFirstName() + " " + newuser.getLastName()).
                                build())).
                build();

    }

}
