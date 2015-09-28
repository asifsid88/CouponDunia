package com.asifsid88.coupondunia.mailserver.service;

import com.asifsid88.coupondunia.model.Email;

import java.util.List;

/**
 * We can have multiple implementations (types) of MailClient, viz., simple mail, Mime-types, HTML Body, etc
 * Currently for demonstrating purpose have implemented only Simple Mail
 */
public interface MailClient {
    void sendMail(Email email);
    void sendBulkMail(List<Email> emailList);
}
