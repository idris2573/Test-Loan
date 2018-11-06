package com.landbay;

import com.google.gson.Gson;
import com.landbay.model.InvestmentRequest;
import com.landbay.model.Loan;
import com.landbay.util.ReadFile;
import com.landbay.util.Util;

import java.util.List;

public class Main {

    public static void main(String[] args) {
//        if(args.length != 2){
//            System.out.println("Please enter the full path of the loans and investment requests files seperated by a space");
//            return;
//        }

        ReadFile readFile = new ReadFile();

//        readFile.readFileUsingFilepath(args[0]);
        readFile.readFileUsingFilepath("H:\\Work\\Landbay - Test\\resources\\loans.csv");
        List<Loan> loans = Util.listToLoans(readFile.getLines());

//        readFile.readFileUsingFilepath(args[1]);
        readFile.readFileUsingFilepath("H:\\Work\\Landbay - Test\\resources\\investmentRequests.csv");
        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(readFile.getLines());

        List<Loan> fundedLoans = Util.fundedLoans(loans, investmentRequests);

        String json = new Gson().toJson(fundedLoans);

        System.out.println(json);
    }
}
