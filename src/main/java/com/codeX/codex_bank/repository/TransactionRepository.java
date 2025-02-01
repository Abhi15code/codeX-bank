package com.codeX.codex_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeX.codex_bank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String>{
    
}
