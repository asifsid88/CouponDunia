package com.asifsid88.coupondunia.core.consumer;

import com.asifsid88.coupondunia.mailserver.MailServerDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Responsible for sending mail
 * It uses MailServer Client and sends mail
 * It is created by EmailConsumerMaster
 */
@Log4j2
class EmailConsumerSlave implements Runnable {
    private final List<Email> emailList;
    private final MailServerDelegate mailServer;

    private Thread thread;

    public EmailConsumerSlave(MailServerDelegate mailServer, List<Email> emailList) {
        this.mailServer = mailServer;
        this.emailList = emailList;
        this.thread = new Thread(this);
    }

    public void send() {
        this.thread.start();
    }

    public void run() {
        log.info("EmailConsumerSlave Thread started: Sending {} Mail", emailList.size());
        mailServer.sendBulkMail(emailList);
    }
}
