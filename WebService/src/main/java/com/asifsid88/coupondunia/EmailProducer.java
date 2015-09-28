package com.asifsid88.coupondunia;

import com.asifsid88.coupondunia.dal.DALServiceDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Thread responsible for populating Email Pool. Scope prototype so that every `request` to sent mail, will
 * fetch fresh data from database
 *
 * Thread will run as long as there is email in DB
 */
@Log4j2
public class EmailProducer implements Runnable {
    private DALServiceDelegate dalServiceDelegate;

    private final Thread thread;

    public EmailProducer(DALServiceDelegate dalServiceDelegate) {
        this.dalServiceDelegate = dalServiceDelegate;
        thread = new Thread(this, "EmailProducer");
    }

    public void start() {
        thread.start();
    }

    public void run() {
        log.info("EmailProducer Thread started!!");
        int numberOfEmailsFetched = 0;
        do {
            List<Email> emailList = dalServiceDelegate.getEmailsInBatch(ConfigValues.numberOfEmailsToGetFromDB);
            numberOfEmailsFetched = emailList.size();
            log.info("Added {} emails in pool", numberOfEmailsFetched);
            if(numberOfEmailsFetched != 0) {
                EmailContainer.putEmailList(emailList);
            }
        } while(numberOfEmailsFetched!=0);
        log.info("No more mails left in Database. Terminating EmailProducer thread");
    }
}
