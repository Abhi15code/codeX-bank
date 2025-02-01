package com.codeX.codex_bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeX.codex_bank.dto.TransactionDto;
import com.codeX.codex_bank.entity.Transaction;
import com.codeX.codex_bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl  implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                                .transactionType(transactionDto.getTransactionType())
                                .accountNumber(transactionDto.getAccountNumber())
                                .amount(transactionDto.getAmount())
                                .status("SUCCESS")
                                .build();


                        transactionRepository.save(transaction);

    }
    
}
