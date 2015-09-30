package com.asifsid88.coupondunia.model;

import lombok.Data;

/**
 * This is a model which is used across system.
 * Having integrated models into every module prevent us from code re-usability and leads to many duplication and difficult system management
 */
@Data
public class Email {
    private long id;
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
    private boolean status;

    /**
     * TODO: Include the following
     * 1. List of attachments
     * 2. CC/ BCC
     * 3. Mail sending date
     */
}
