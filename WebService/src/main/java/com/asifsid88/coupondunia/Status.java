package com.asifsid88.coupondunia;

public enum Status {
    INPROGRESS("inprogress")
    , COMPLETE("complete");

    private String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
