package com.codeX.codex_bank.service;

import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.TransactionDto;

@Service
public interface TransactionService {

    void saveTransaction(TransactionDto transactionDto);
    
}
