package com.asifsid88.coupondunia.property;

/**
 * This maps to the database table `Email` column names (to be precise select clause names)
 */
public interface EmailProperty {
    String ID = "id";
    String FROM_EMAIL = "fromEmail";
    String TO_EMAIL = "toEmail";
    String SUBJECT = "subject";
    String BODY = "body";
    String STATUS = "status";
}
