package com.example.harish.proschoolforparentadvanced.suresh.feecollection;

/**
 * Created by USER on 11/16/2017.
 */

public class FeeCollection {

    private String feeType;

    public FeeCollection(String feeType, String feeCategory, String paymentMode, String amount, String fine, String discount, String total,String feePaid) {
        this.feeType = feeType;
        this.feeCategory = feeCategory;
        this.paymentMode = paymentMode;
        this.amount = amount;
        this.fine = fine;
        this.discount = discount;
        this.total = total;
        this.feePaid=feePaid;
    }

    private String feeCategory;
    private String paymentMode;

    public String getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(String feePaid) {
        this.feePaid = feePaid;
    }

    private String amount;
    private String fine;
    private String feePaid;

    private String discount;
    private String total;

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeCategory() {
        return feeCategory;
    }

    public void setFeeCategory(String feeCategory) {
        this.feeCategory = feeCategory;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
