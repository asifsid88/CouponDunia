package com.asifsid88.coupondunia.core.producer;

import com.asifsid88.coupondunia.core.ConfigValues;
import com.asifsid88.coupondunia.core.EmailContainer;
import com.asifsid88.coupondunia.core.EmailContainerObserver;
import com.asifsid88.coupondunia.dal.DALServiceDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Producer is a thread- responsible for fetching values from database (using DAL - Data Access Layer- to get data from DB)
 * and populating emailContainer
 *
 * It is a thread which will run every 5 seconds (configurable) to check database for newer mails [Consider it as a Scheduled Job]
 *
 * Producer will get at a time 500 (configurable) mails from DB till all mails are fetched
 *
 * As an example, if there are 1500 mails in DB, then producer will get all of them into pool in 3 calls (every call
 * getting 500 mails `configurable value`). Once all the calls are done, then it will wait for 5 seconds and again pull from DB
 * maintaining a cooling period of 5 second (configurable value)
 *
 */
@Component("emailProducer")
@Log4j2
public class EmailProducer implements EmailContainerObserver, Runnable {

    /*
    Container which is populated from DB. Common with Consumer
     */
    private final EmailContainer container;
    private final DALServiceDelegate dalService;

    @Autowired
    public EmailProducer(EmailContainer container, DALServiceDelegate dalService) {
        this.container = container;
        this.dalService = dalService;

        /*
        Register producer to container
         */
        this.container.register(this);
    }

    /*
    It is a thread which will check database for newer mails (at interval of 5 seconds -- configurable)
    The interval should be set keeping in mind the `load` database can take or upon business need
     */
    public void trigger() {
        while(true) {
            populateContainer();
            waitTillCoolingPeriod();
        }
    }

    private void populateContainer() {
        List<Email> emailList = dalService.getEmailsInBatch(ConfigValues.numberOfEmailsToGetFromDB);
        log.info("{} emails fetch from DB", emailList.size());
        if(emailList.size() > 0) {
            container.putEmailList(emailList, this);
        }
    }

    private void waitTillCoolingPeriod() {
        try {
            log.info("Waiting for cooling period of {} ms", ConfigValues.waitToCheckMailFromDB);
            Thread.sleep(ConfigValues.waitToCheckMailFromDB);
        } catch (InterruptedException e) {
            log.error("Exception occurred while waiting, Exception: {} ", e);
        } catch(Exception e) {
            log.error("Exception in EmailProducer: {} ", e);
        }
    }

    public void run() {
        log.info("EmailProducer Thread initiated!");
        trigger();
        log.info("EmailProducer Thread terminated!");
    }

    public String getName() {
        return "emailProducer";
    }
}
