package com.codeX.codex_bank.entity;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;

import com.codeX.codex_bank.dto.BankResponse;
import com.codeX.codex_bank.dto.TransactionDto;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
 
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;
    private String status;
    @CurrentTimestamp
    private LocalDate createdAt;

    
    
}
