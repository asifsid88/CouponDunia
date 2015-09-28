package com.asifsid88.coupondunia.mailserver;

import com.asifsid88.coupondunia.mailserver.service.MailClient;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServerDelegate {

    private static MailClient simpleMailService;

    @Autowired
    public void setMailClient(@Qualifier("simpleMailService") MailClient mailClient) {
        MailServerDelegate.simpleMailService = mailClient;
    }

    public void sendMail(Email email) {
        simpleMailService.sendMail(email);
    }

    public void sendBulkMail(List<Email> emailList) {
        simpleMailService.sendBulkMail(emailList);
    }
}
