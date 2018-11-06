package com.landbay.util;

import com.landbay.model.InvestmentRequest;
import com.landbay.model.Loan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {

    public static List<Loan> listToLoans(List<String> list){

        if(list == null || list.isEmpty()){
            System.err.println("List is null or is empty");
            return null;
        }

        List<Loan> loans = new ArrayList<>();

        for(String line : list){

            if(line.startsWith("loanId")){
                continue;
            }

            String[] lineArray = line.split(",");
            if(lineArray.length != 5){
                System.err.println("Line does not have enough columns");
                return null;
            }

            try {
                Loan loan = new Loan();
                loan.setId(Long.parseLong(lineArray[0]));
                loan.setAmount(Integer.parseInt(lineArray[1]));
                loan.setProductType(lineArray[2]);
                loan.setTerm(Integer.parseInt(lineArray[3]));

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                loan.setCompletedDate(dateFormat.parse(lineArray[4]));

                loans.add(loan);
            }catch (Exception e){
                System.err.println("Line has wrong format");
                return null;
            }
        }

        return loans;
    }

    public static List<InvestmentRequest> listToInvestmentRequests(List<String> list){

        if(list == null || list.isEmpty()){
            System.err.println("List is null or is empty");
            return null;
        }

        List<InvestmentRequest> investmentRequests = new ArrayList<>();

        for(String line : list){

            if(line.startsWith("investor")){
                continue;
            }

            String[] lineArray = line.split(",");
            if(lineArray.length != 4){
                System.err.println("Line does not have enough columns");
                return null;
            }

            try {
                InvestmentRequest investmentRequest = new InvestmentRequest();
                investmentRequest.setInvestor(lineArray[0]);
                investmentRequest.setAmount(Integer.parseInt(lineArray[1]));
                investmentRequest.setProductType(lineArray[2]);
                investmentRequest.setTerm(Integer.parseInt(lineArray[3]));

                investmentRequests.add(investmentRequest);
            }catch (Exception e){
                System.err.println("Line has wrong format");
                return null;
            }
        }

        return investmentRequests;
    }

    // returns only funded loans
    public static List<Loan> fundedLoans(List<Loan> loans, List<InvestmentRequest> investmentRequests){
        List<Loan> fundedLoans = new ArrayList<>();

        if(loans == null || loans.isEmpty() || investmentRequests == null || investmentRequests.isEmpty()){
            System.err.println("There are no loans or investment request");
            return null;
        }

        Collections.sort(loans);

        for(Loan loan : loans){

            List<InvestmentRequest> investmentRequestsByType = getByProductType(loan.getProductType(), investmentRequests);

            if(investmentRequestsByType.isEmpty()){
                continue;
            }

            if(fundLoan(loan, investmentRequestsByType)){
                fundedLoans.add(loan);
            }

        }

        if(fundedLoans.isEmpty()){
            System.out.println("There are no funded loans");
        }

        return fundedLoans;
    }

    public static List<InvestmentRequest> getByProductType(String productType, List<InvestmentRequest> investmentRequests){
        List<InvestmentRequest> investmentRequestsByType = new ArrayList<>();

        for(InvestmentRequest investmentRequest : investmentRequests){
            if(investmentRequest.getProductType().equalsIgnoreCase(productType)){
                investmentRequestsByType.add(investmentRequest);
            }
        }

        return investmentRequestsByType;
    }

    public static boolean fundLoan(Loan loan, List<InvestmentRequest> investmentRequests){

        if(loan == null || investmentRequests == null){
            System.out.println("Loan or investment request is null");
            return false;
        }

        removeInvalidInvestmentRequests(loan, investmentRequests);

        if(investmentRequests.isEmpty()){
            return false;
        }

        if(cumulativeFundsAvailable(investmentRequests) < loan.getAmount()){
            return false;
        }

        // sort investment requests from highest to lowest
        Collections.sort(investmentRequests);

        int loanAmount = loan.getAmount();

        for(InvestmentRequest investmentRequest : investmentRequests){

            if(investmentRequest.getAmount() >= loan.getAmount()){
                InvestmentRequest fundRequest = new InvestmentRequest();
                fundRequest.setInvestor(investmentRequest.getInvestor());
                fundRequest.setAmount(loan.getAmount());
                fundRequest.setProductType(investmentRequest.getProductType());
                fundRequest.setTerm(investmentRequest.getTerm());

                loan.getInvestmentRequests().add(fundRequest);

                investmentRequest.setAmount(investmentRequest.getAmount() - loan.getAmount());

                return true;

            } else {
                InvestmentRequest fundRequest = new InvestmentRequest();
                fundRequest.setInvestor(investmentRequest.getInvestor());
                fundRequest.setProductType(investmentRequest.getProductType());
                fundRequest.setTerm(investmentRequest.getTerm());

                if(loanAmount - investmentRequest.getAmount() < 0){
                    fundRequest.setAmount(loanAmount);
                    loanAmount = 0;
                    investmentRequest.setAmount(loan.getAmount() - investmentRequest.getAmount());
                } else {
                    fundRequest.setAmount(investmentRequest.getAmount());
                    loanAmount = loanAmount - investmentRequest.getAmount();
                    investmentRequest.setAmount(investmentRequest.getAmount() - loan.getAmount());
                }

                loan.getInvestmentRequests().add(fundRequest);

                if(loanAmount == 0){
                    return true;
                }
            }
        }

        return false;
    }

    public static void removeInvalidInvestmentRequests(Loan loan, List<InvestmentRequest> investmentRequests){
        List<InvestmentRequest> delete = new ArrayList<>();

        for(InvestmentRequest investmentRequest : investmentRequests){
            if(investmentRequest.getTerm() < loan.getTerm()){
                delete.add(investmentRequest);
            }
        }

        investmentRequests.removeAll(delete);
    }

    public static int cumulativeFundsAvailable(List<InvestmentRequest> investmentRequests){
        int amount = 0;

        for(InvestmentRequest investmentRequest : investmentRequests){
            amount = amount + investmentRequest.getAmount();
        }

        return amount;
    }

}
