package com.asifsid88.coupondunia.core;

/**
 * This can be made configurable by placing it in Properties file
 */
public interface ConfigValues {
    // Waiting time for consumer to wait for email to be available in pool
    long waitForEmailInPoolToArrive = 3;    // 3 seconds

    // Number of emails to be picked at once from email pool
    int numberOfEmailsToSend = 300;

    // Numer of emails to get from DB to populate email pool
    int numberOfEmailsToGetFromDB = 500;
}
