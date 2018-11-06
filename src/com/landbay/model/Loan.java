package com.landbay.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan implements Comparable<Loan>{

    private Long id;
    private int amount;
    private String productType;
    private int term;
    private Date completedDate;
    private List<InvestmentRequest> investmentRequests = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public List<InvestmentRequest> getInvestmentRequests() {
        return investmentRequests;
    }

    public void setInvestmentRequests(List<InvestmentRequest> investmentRequests) {
        this.investmentRequests = investmentRequests;
    }

    @Override
    public int compareTo(Loan o) {
        return getCompletedDate().compareTo(o.completedDate);
    }
}
