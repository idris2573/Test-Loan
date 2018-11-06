package com.landbay.util;

import com.landbay.model.InvestmentRequest;
import com.landbay.model.Loan;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void listToLoansSuccess() {

        List<String> list = new ArrayList<>();
        list.add("1,100000,FIXED,18,2015-01-01");
        list.add("2,99000,FIXED,10,2015-01-02");
        list.add("3,100000,FIXED,10,2015-01-03");
        list.add("4,85000,TRACKER,24,2015-01-04");
        list.add("5,90000,TRACKER,24,2015-01-05");
        list.add("6,120000,FIXED,11,2015-01-06");
        list.add("7,80000,TRACKER,24,2015-01-07");
        list.add("8,77700,TRACKER,24,2015-01-08");
        list.add("9,110000,TRACKER,24,2015-01-09");

        List<Loan> loans = Util.listToLoans(list);

        assertNotNull(loans);
        assertFalse(loans.isEmpty());
    }

    @Test
    public void listToLoansWithBadData() {

        List<String> list = new ArrayList<>();
        list.add("1,100000,FIXED,18,2015-01-01");
        list.add("2,99000,FIXED10,2015-01-02");
        list.add("3,100000,FIXED,10,2015-01-03");
        list.add("4,85000TRACKER,242015-01-04");

        List<Loan> loans = Util.listToLoans(list);

        assertNull(loans);
    }

    @Test
    public void listToInvestmentRequestsSuccess() {

        List<String> list = new ArrayList<>();
        list.add("Alice,100,FIXED,12");
        list.add("Bob,330000,TRACKER,30");
        list.add("Charlie,1000,FIXED,12");
        list.add("Dan,20000,TRACKER,50");
        list.add("Eve,13000,TRACKER,30");

        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(list);

        assertNotNull(investmentRequests);
        assertFalse(investmentRequests.isEmpty());
    }

    @Test
    public void listToInvestmentRequestsWithBadData() {

        List<String> list = new ArrayList<>();
        list.add("Alice,100,FIXED");
        list.add("Bob,330000,TRACKER");
        list.add("Charlie,1000,FIXED");
        list.add("Dan,20000,TRACKER");
        list.add("Eve,13000,TRACKER");

        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(list);

        assertNull(investmentRequests);
    }

    @Test
    public void fundLoansSuccess(){
        List<String> loansList = new ArrayList<>();
        loansList.add("3,100000,FIXED,10,2015-01-03");
        loansList.add("1,100000,FIXED,18,2015-01-01");
        loansList.add("9,110000,TRACKER,24,2015-01-09");
        loansList.add("2,99000,FIXED,10,2015-01-02");
        loansList.add("8,77700,TRACKER,24,2015-01-08");
        loansList.add("7,80000,TRACKER,24,2015-01-07");
        loansList.add("4,85000,TRACKER,24,2015-01-04");
        loansList.add("6,120000,FIXED,11,2015-01-06");
        loansList.add("5,90000,TRACKER,24,2015-01-05");

        List<Loan> loans = Util.listToLoans(loansList);

        List<String> investmentRequestsList = new ArrayList<>();
        investmentRequestsList.add("Alice,100,FIXED,12");
        investmentRequestsList.add("Bob,330000,TRACKER,30");
        investmentRequestsList.add("Charlie,1000,FIXED,12");
        investmentRequestsList.add("Dan,20000,TRACKER,50");
        investmentRequestsList.add("Eve,13000,TRACKER,30");
        investmentRequestsList.add("Faith,40,FIXED,12");
        investmentRequestsList.add("Gary,1300,FIXED,12");
        investmentRequestsList.add("Helgor,400000,FIXED,12");
        investmentRequestsList.add("India,5670,FIXED,12");
        investmentRequestsList.add("Jane,100,FIXED,12");
        investmentRequestsList.add("Klaus,2000,FIXED,12");
        investmentRequestsList.add("Leo,67800,TRACKER,30");
        investmentRequestsList.add("Mark,180,TRACKER,30");
        investmentRequestsList.add("Nina,10000,TRACKER,30");

        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(investmentRequestsList);

        List<Loan> fundedLoans = Util.fundedLoans(loans, investmentRequests);
        assertNotNull(fundedLoans);
        assertFalse(fundedLoans.isEmpty());
    }

    @Test
    public void fundLoansWithLoansNull(){

        List<String> investmentRequestsList = new ArrayList<>();
        investmentRequestsList.add("Alice,100,FIXED,12");
        investmentRequestsList.add("Bob,330000,TRACKER,30");
        investmentRequestsList.add("Charlie,1000,FIXED,12");
        investmentRequestsList.add("Dan,20000,TRACKER,50");
        investmentRequestsList.add("Eve,13000,TRACKER,30");
        investmentRequestsList.add("Faith,40,FIXED,12");
        investmentRequestsList.add("Gary,1300,FIXED,12");
        investmentRequestsList.add("Helgor,400000,FIXED,12");
        investmentRequestsList.add("India,5670,FIXED,12");
        investmentRequestsList.add("Jane,100,FIXED,12");
        investmentRequestsList.add("Klaus,2000,FIXED,12");
        investmentRequestsList.add("Leo,67800,TRACKER,30");
        investmentRequestsList.add("Mark,180,TRACKER,30");
        investmentRequestsList.add("Nina,10000,TRACKER,30");

        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(investmentRequestsList);

        assertNull(Util.fundedLoans(null, investmentRequests));
    }

    @Test
    public void fundLoansWithLoansEmpty(){
        List<String> loansList = new ArrayList<>();
        List<Loan> loans = Util.listToLoans(loansList);

        List<String> investmentRequestsList = new ArrayList<>();
        investmentRequestsList.add("Alice,100,FIXED,12");
        investmentRequestsList.add("Bob,330000,TRACKER,30");
        investmentRequestsList.add("Charlie,1000,FIXED,12");
        investmentRequestsList.add("Dan,20000,TRACKER,50");
        investmentRequestsList.add("Eve,13000,TRACKER,30");
        investmentRequestsList.add("Faith,40,FIXED,12");
        investmentRequestsList.add("Gary,1300,FIXED,12");
        investmentRequestsList.add("Helgor,400000,FIXED,12");
        investmentRequestsList.add("India,5670,FIXED,12");
        investmentRequestsList.add("Jane,100,FIXED,12");
        investmentRequestsList.add("Klaus,2000,FIXED,12");
        investmentRequestsList.add("Leo,67800,TRACKER,30");
        investmentRequestsList.add("Mark,180,TRACKER,30");
        investmentRequestsList.add("Nina,10000,TRACKER,30");

        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(investmentRequestsList);

        assertNull(Util.fundedLoans(loans, investmentRequests));
    }

    @Test
    public void fundLoansWithInvestmentsNull(){
        List<String> loansList = new ArrayList<>();
        loansList.add("3,100000,FIXED,10,2015-01-03");
        loansList.add("1,100000,FIXED,18,2015-01-01");
        loansList.add("9,110000,TRACKER,24,2015-01-09");
        loansList.add("2,99000,FIXED,10,2015-01-02");
        loansList.add("8,77700,TRACKER,24,2015-01-08");
        loansList.add("7,80000,TRACKER,24,2015-01-07");
        loansList.add("4,85000,TRACKER,24,2015-01-04");
        loansList.add("6,120000,FIXED,11,2015-01-06");
        loansList.add("5,90000,TRACKER,24,2015-01-05");

        List<Loan> loans = Util.listToLoans(loansList);

        assertNull(Util.fundedLoans(loans, null));
    }

    @Test
    public void fundLoansWithInvestmentsEmpty(){
        List<String> loansList = new ArrayList<>();
        loansList.add("3,100000,FIXED,10,2015-01-03");
        loansList.add("1,100000,FIXED,18,2015-01-01");
        loansList.add("9,110000,TRACKER,24,2015-01-09");
        loansList.add("2,99000,FIXED,10,2015-01-02");
        loansList.add("8,77700,TRACKER,24,2015-01-08");
        loansList.add("7,80000,TRACKER,24,2015-01-07");
        loansList.add("4,85000,TRACKER,24,2015-01-04");
        loansList.add("6,120000,FIXED,11,2015-01-06");
        loansList.add("5,90000,TRACKER,24,2015-01-05");

        List<Loan> loans = Util.listToLoans(loansList);

        List<String> investmentRequestsList = new ArrayList<>();
        List<InvestmentRequest> investmentRequests = Util.listToInvestmentRequests(investmentRequestsList);

        assertNull(Util.fundedLoans(loans, investmentRequests));
    }
}
