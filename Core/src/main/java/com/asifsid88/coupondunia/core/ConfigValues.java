package com.asifsid88.coupondunia.core;

/**
 * This can be made configurable by placing it in Properties file
 */
public interface ConfigValues {
    // Waiting time for consumer to wait for email to be available in pool- Default: 3 seconds
    long waitForEmailInPoolToArrive = 3;

    // Number of emails to be picked at once from email pool- Default: 300
    int numberOfEmailsToSend = 300;

    // Numer of emails to get from DB to populate email pool- Default: 500
    int numberOfEmailsToGetFromDB = 500;

    // Wait for number of seconds before checking DB (used by producer)- Default: 5 seconds
    int waitToCheckMailFromDB = 5000;   // 5 seconds
}
