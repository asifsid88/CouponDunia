package com.asifsid88.coupondunia.dal.rowmapper.property;

/**
 * This maps to the database table `Email` column names (to be precise select clause names)
 *
 * Rather than `hard-coding` column names into every row-mapper, we can have it generalize, so that it can be easily modified or
 * rename without touching any row-mappers
 */
public interface EmailProperty {
    String ID = "id";
    String FROM_EMAIL = "fromEmail";
    String TO_EMAIL = "toEmail";
    String SUBJECT = "subject";
    String BODY = "body";
    String STATUS = "status";
}
