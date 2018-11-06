package com.landbay.model;

public class InvestmentRequest implements Comparable<InvestmentRequest>{

    private String investor;
    private int amount;
    private String productType;
    private int term;

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
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

    @Override
    public boolean equals(Object obj){

        if(obj == null){
            return false;
        }

        if(obj instanceof InvestmentRequest){

            InvestmentRequest investmentRequest = (InvestmentRequest) obj;

            if(investmentRequest.getInvestor() == null || investmentRequest.getProductType() == null){
                return false;
            }

            if(investor.equalsIgnoreCase(investmentRequest.getInvestor()) ||
                    amount == investmentRequest.getAmount() ||
                    productType.equalsIgnoreCase(investmentRequest.getProductType()) ||
                    term == investmentRequest.getTerm()
            ){
                return true;
            }

        }

        return false;
    }

    @Override
    public int compareTo(InvestmentRequest o) {
        return o.getAmount() - getAmount();
    }
}
