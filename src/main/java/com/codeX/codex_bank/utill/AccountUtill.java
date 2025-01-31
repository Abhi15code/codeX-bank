package com.codeX.codex_bank.utill;

import java.time.Year;

public class AccountUtill {

    public static String ACCOUNT_EXISTS_CODE = "001";
    public static String ACCOUNT_EXISTS_MESSAGE = "Account already exists";
  
    public static String ACCOUNT_CREATED_CODE = "002";
    public static String ACCOUNT_CREATED_MESSAGE = "Account created successfully";

    public static String generateAccountNumber(){
        Year currentyear = Year.now();

        int min = 100000;
        int max = 999999;
        // geenrate random account number between min and max
    
        int randNumber = (int)Math.floor(Math.random() * (max-min+1)+min);
    
    
        // convert the current and randomnumber to String then concate
    
        String year = String.valueOf(currentyear);
        String randomNumber = String.valueOf(randNumber);
    
    
        StringBuilder accountNumber = new StringBuilder();
    

        return accountNumber.append(year).append(randomNumber).toString();
    }



}
