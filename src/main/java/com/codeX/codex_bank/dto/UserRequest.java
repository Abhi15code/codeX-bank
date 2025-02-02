package com.codeX.codex_bank.dto;


import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
 private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String email;
    private String password;
    private String phoneNumber;
    // private String accountNumber;
    // private BigDecimal accountBalance;

    @CurrentTimestamp
    private String creationTime;
    @UpdateTimestamp
    private String updateTime;
}
