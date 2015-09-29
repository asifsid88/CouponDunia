package com.asifsid88.coupondunia.core;

/**
 * This can be made configurable by placing it in Properties file
 */
public interface ConfigValues {
    // Waiting time for consumer to wait for email to be available in pool- say: 3 seconds
    long waitForEmailInPoolToArrive = 3;    // in seconds

    // Number of emails to be picked at once from email pool- say: 300
    int numberOfEmailsToSend = 3;

    // Numer of emails to get from DB to populate email pool- say: 500
    int numberOfEmailsToGetFromDB = 10;

    // Wait for number of seconds before checking DB (used by producer)- say: 5 seconds
    int waitToCheckMailFromDB = 5000;       // in milli-seconds
}
