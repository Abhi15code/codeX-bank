package com.codeX.codex_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeX.codex_bank.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    // Boolean existsByAccountNumber(String accountNumber);
    



}
