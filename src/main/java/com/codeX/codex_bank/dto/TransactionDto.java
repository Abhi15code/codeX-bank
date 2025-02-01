package com.codeX.codex_bank.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
     private Long transactionId;
    private String transactionType;
    private BigDecimal amount;
    private String accountNumber;
    private String status;

}
