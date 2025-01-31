package com.codeX.codex_bank.utill;

import java.time.Year;

public class AccountUtill {

    public static String ACCOUNT_EXISTS_CODE = "001";
    public static String ACCOUNT_EXISTS_MESSAGE = "Account already exists";

    public static String USER_FOUND_CODE = "003";
    public static String USER_FOUND_MESSAGE = "User found successfully";

    public static String USER_NOT_EXISTS_CODE = "004";
    public static String USER_NOT_EXISTS_MESSAGE = "User not found";
  
    public static String ACCOUNT_CREATED_CODE = "002";
    public static String ACCOUNT_CREATED_MESSAGE = "Account created successfully";

    public static String ACCOUNT_CREDITED_CODE = "005";
    public static String ACCOUNT_CREDITED_MESSAGE = "Account credited successfully";

    
    public static String INSUFFICIENT_BALANCE_CODE = "006";
    public static String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient balance";

    public static String ACCOUNT_DEBITED_CODE = "007";
    public static String ACCOUNT_DEBITED_MESSAGE = "Account debited successfully";
    

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
