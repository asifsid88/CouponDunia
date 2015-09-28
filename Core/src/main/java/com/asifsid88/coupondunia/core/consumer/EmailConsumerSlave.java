package com.asifsid88.coupondunia.core.consumer;

import com.asifsid88.coupondunia.mailserver.MailServerDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Responsible for sending mail
 * It uses MailServer Client and sends mail
 */
@Log4j2
public class EmailConsumerSlave {
    private List<Email> emailList;
    private MailServerDelegate mailServer;

    public EmailConsumerSlave(MailServerDelegate mailServer, List<Email> emailList) {
        this.mailServer = mailServer;
        this.emailList = emailList;
    }

    public void send() {
        log.trace("EmailConsumerSlave: Sending {} Mail", emailList.size());
        mailServer.sendBulkMail(emailList);
    }
}
