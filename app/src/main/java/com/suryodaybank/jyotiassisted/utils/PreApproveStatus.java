package com.suryodaybank.jyotiassisted.utils;

public enum PreApproveStatus {
    INITIATED("initiated"),
    NOT_INTERESTED("Not Interested"),
    COMPLETED("completed");

    public String status;

    PreApproveStatus(String status) {
        this.status = status;
    }
}
