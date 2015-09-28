package com.asifsid88.coupondunia.dal;

import com.asifsid88.coupondunia.dal.service.EmailService;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
