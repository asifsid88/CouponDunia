package com.asifsid88.coupondunia;

import com.asifsid88.coupondunia.mailserver.MailServerDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class EmailSender {

    @Autowired
    private MailServerDelegate mailServerDelegate;

    public int sendBulkMail() {
        List<Email> emailList = EmailContainer.takeEmailList();
        int size = emailList.size();
        log.info("Consumed {} mails from pool", size);
        mailServerDelegate.sendBulkMail(emailList);

        return size;
    }

    public int sendMail() {
        Email email = EmailContainer.takeEmail();
        int mailSendCount = 0;
        if(email == null) {
            log.info("Nothing to consume from pool, no email send");
        } else {
            log.info("Consumed 1 mail from pool");
            mailServerDelegate.sendMail(email);
            mailSendCount = 1;
        }

        return mailSendCount;
    }
}
