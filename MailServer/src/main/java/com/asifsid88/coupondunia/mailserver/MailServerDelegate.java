package com.asifsid88.coupondunia.mailserver;

import com.asifsid88.coupondunia.mailserver.service.MailClient;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade Layer which is exposed to the client to use MailServer services
 *
 * Rather than exposing every services to client, it is a good idea to have Facade layer (delegator)
 * All the services will be present in this layer so the client has to inject or use only one class to use all services
 * This also gives us privilege to increase or decrease any number of services (at any time) without affecting or requiring any changes
 * from client's end
 */
@Service
public class MailServerDelegate {

    private final MailClient simpleMailService;

    @Autowired
    public MailServerDelegate(@Qualifier("simpleMailService") MailClient simpleMailService) {
        this.simpleMailService = simpleMailService;
    }

    public void sendMail(Email email) {
        simpleMailService.sendMail(email);
    }

    public void sendBulkMail(List<Email> emailList) {
        simpleMailService.sendBulkMail(emailList);
    }
}
