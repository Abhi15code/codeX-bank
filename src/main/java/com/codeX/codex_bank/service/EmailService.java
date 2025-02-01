package com.codeX.codex_bank.service;

import com.codeX.codex_bank.dto.EmailDetails;

public interface EmailService {
    void sendAlert(EmailDetails emailDetails);
    void sendEmailWithAttachment(EmailDetails emailDetails);
    

}
