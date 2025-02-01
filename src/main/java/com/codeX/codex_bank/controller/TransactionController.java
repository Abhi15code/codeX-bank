package com.codeX.codex_bank.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeX.codex_bank.entity.Transaction;
import com.codeX.codex_bank.service.BankStatement;
import com.itextpdf.text.DocumentException;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/bankstatememt")
@AllArgsConstructor

public class TransactionController {

    @Autowired
    BankStatement bankStatement;

    @GetMapping
    public List<Transaction> generateTransactions(@RequestParam String accountNumber,
            @RequestParam String startDate,
            @RequestParam String endDate) throws FileNotFoundException, DocumentException
           {
             return   bankStatement.generateTransactions(accountNumber, startDate, endDate);

    }


}
