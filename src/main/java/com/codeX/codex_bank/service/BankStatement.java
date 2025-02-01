package com.codeX.codex_bank.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeX.codex_bank.dto.EmailDetails;
import com.codeX.codex_bank.entity.Transaction;
import com.codeX.codex_bank.entity.User;
import com.codeX.codex_bank.repository.TransactionRepository;
import com.codeX.codex_bank.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
// @AllArgsConstructor
@Slf4j
public class BankStatement {

    
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    

    private static final String FILE = "C:\\Users\\abhis\\Documents\\mystatement.pdf";


    /*
     * retrive list of transaction within a date range given a account number
     * * generate a pdf file of transaction
     * send the file via email
     */

    public List<Transaction> generateTransactions(String accountNumber,String startDate , String endDate)  throws FileNotFoundException, DocumentException{

        LocalDate start = LocalDate.parse(startDate , DateTimeFormatter.ISO_DATE);
        LocalDate end = LocalDate.parse( endDate, DateTimeFormatter.ISO_DATE);

            List<Transaction>  transactionList = transactionRepository.findAll().
            stream().filter(transaction->transaction.getAccountNumber().equals(accountNumber)).
            filter(transaction->transaction.getCreatedAt().isEqual(start)).filter(transaction->transaction.getCreatedAt().isEqual(end))
            .toList();

        User user = userRepository.findByAccountNumber(accountNumber);
        
        String customerName = user.getFirstName()+" "+user.getLastName();

        Rectangle statementSize = new Rectangle(PageSize.A4);
        Document document = new Document(statementSize);
        log.info("setting size of Document");
        FileOutputStream outPutStream = new FileOutputStream(FILE);
        PdfWriter.getInstance(document, outPutStream);
        document.open();

        PdfPTable bankInfoTable = new PdfPTable(1);
        PdfPCell bankName = new PdfPCell(new Phrase("The CodeX Bank"));
        bankName.setBorder(0);
        bankName.setBackgroundColor(BaseColor.PINK);
        bankName.setPadding(20f);

        PdfPCell bankAdress = new PdfPCell(new Phrase("A-165, A-Block, New Ashok Nagar , New Delhi , 110096"));
        bankAdress.setBorder(0);

        bankInfoTable.addCell(bankName);
        bankInfoTable.addCell(bankAdress);

        PdfPTable statementInfo = new PdfPTable(2);
        PdfPCell customerInfo = new PdfPCell(new Phrase("Strart Date: " + startDate));

        customerInfo.setBorder(0);
        PdfPCell stopDate = new PdfPCell(new Phrase("END DATE: "+endDate));

        stopDate.setBorder(0);  

        PdfPCell statement = new PdfPCell(new Phrase("STATEMENT OF ACCOUNT"));
        statement.setBorder(0);

       

        PdfPCell name = new PdfPCell(new Phrase("CUSTOMER NAME "+customerName));
        name.setBorder(0);
        PdfPCell space  = new PdfPCell();
        space.setBorder(0);
        PdfPCell adress = new PdfPCell(new Phrase("Customerr Adress "+user.getAddress()));
        adress.setBorder(0);



        PdfPTable transactionTable = new PdfPTable(4);
        PdfPCell date = new PdfPCell(new Phrase("DATE"));
        date.setBackgroundColor(BaseColor.PINK);
        date.setBorder(0);




        PdfPCell transactionType = new PdfPCell(new Phrase("TRANSACTION TYPE"));
        transactionType.setBackgroundColor(BaseColor.PINK);
        transactionType.setBorder(0);
        PdfPCell transactionAmount = new PdfPCell(new Phrase("TRANSACTION AMOUNT"));
        transactionAmount.setBackgroundColor(BaseColor.PINK);
        transactionAmount.setBorder(0);
        PdfPCell transactionStatus = new PdfPCell(new Phrase("STATUS"));
        transactionStatus.setBackgroundColor(BaseColor.PINK);
        transactionStatus.setBorder(0);

        transactionTable.addCell(date);
        transactionTable.addCell(transactionType);
        transactionTable.addCell(transactionAmount);
        transactionTable.addCell(transactionStatus);
        
        transactionList.forEach(transaction-> {
            transactionTable.addCell(new Phrase(transaction.getCreatedAt().toString()));
            transactionTable.addCell(new Phrase(transaction.getTransactionType()));
            transactionTable.addCell(new Phrase(transaction.getAmount().toString()));
            transactionTable.addCell(new Phrase(transaction.getStatus()));
        }
        );

        statementInfo.addCell(customerInfo);
        statementInfo.addCell(statement);
        statementInfo.addCell(endDate);
        statementInfo.addCell(name);
        statementInfo.addCell(space);
        statementInfo.addCell(adress);


        document.add(bankInfoTable);
        document.add(statementInfo);
        document.add(transactionTable);




        document.close();



        EmailDetails emailDetails = EmailDetails.builder().
                                    reciptient(user.getEmail())
                                    .subject("STATEMENT OF ACCOUNT")
                                    .messageBody("kindly find your requested Account statement ").
                                    attatchment(FILE).
                                     build();
        
     
            emailService.sendEmailWithAttachment(emailDetails);


            return transactionList;

        }

}
