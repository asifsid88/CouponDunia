package com.asifsid88.coupondunia.dal;

import com.asifsid88.coupondunia.dal.service.EmailService;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Facade Layer which is exposed to the client to use DAL services
 *
 * Rather than exposing every services to client, it is a good idea to have Facade layer (delegator)
 * All the services will be present in this layer so the client has to inject or use only one class to use all services
 * This also gives us privilege to increase or decrease any number of services (at any time) without affecting or requiring any changes
 * from client's end
 */
@Service
public final class DALServiceDelegate {

    private static EmailService emailService;

    @Autowired
    public void setEmailService(@Qualifier("emailService") EmailService emailService) {
        DALServiceDelegate.emailService = emailService;
    }

    public List<Email> getEmail() {
        return emailService.getEmail();
    }

    public List<Email> getEmailsByIdsInBatch(Long id, int size) {
        return emailService.getEmailsByIdsInBatch(id, size);
    }

    public List<Email> getEmailsInBatch(int size) {
        return emailService.getEmailsInBatch(size);
    }
}
