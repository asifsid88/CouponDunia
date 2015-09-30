package com.asifsid88.coupondunia.core.consumer;

import com.asifsid88.coupondunia.core.ConfigValues;
import com.asifsid88.coupondunia.core.EmailContainer;
import com.asifsid88.coupondunia.core.EmailContainerObserver;
import com.asifsid88.coupondunia.mailserver.MailServerDelegate;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Consumer responsible for consuming `mail` object from pool and using `MailServer` to send mails
 *
 * Consumer Object doesn't have to be a continuously running thread
 * Rather, consumer should be notified when `new mail objects` are available in Pool
 *
 * As soon as new mail objects are created, then consumer will be notified and it will create as many thread
 * as required to sent mail parallel, thereby sending mails concurrently.
 * Once mails are sent, then all these newly created threads are destroyed, hence maintaining space efficient
 * (i.e., not wasting memory usage)
 *
 *
 * As an example, if say 1500 mails to be send, and say at a time 300 (configurable) mails can be sent. Then Master
 * will create 5 ConsumerSlave each sending 300 mails. After all of them are sent then these slaves are destroyed
 * If next time 600 mails to be send, then only 2 ConsumerSlave will be created and so on
 *
 * Consumer will start only when Producer produces something and notifies consumer
 *
 */
@Component
@Log4j2
public class EmailConsumerMaster implements EmailContainerObserver {

    private EmailContainer container;
    private MailServerDelegate mailServer;

    @Autowired
    public EmailConsumerMaster(EmailContainer container, MailServerDelegate mailServer) {
        this.container = container;
        this.mailServer = mailServer;

        /*
        Register consumer to container
         */
        this.container.register(this);
    }

    /*
    Invoked only when new data are available in container
     */
    public void trigger() {
        /*
         Set `isConsumed` to TRUE, indicating Consumer is already polling from Pool
         There is a possibility, where consumer is already feeding from pool and producer produces a newer value
         In this case, this flag will ensure `Consumer` is not triggered unnecessarily
          */
        container.setIsConsumed(Boolean.TRUE);

        /*
        Create Slave Thread- why Thread? So that Consumer will be busy in eating and Producer will keep on populating more data
        in the pool. This will ensure producer is not blocked until consumer finishes. Hence we achieve simultaneous population of
        pool and consumption of pool
         */
        new CreateSlave().create();
    }

    public String getName() {
        return "emailConsumerMaster";
    }

    private class CreateSlave implements Runnable {
        private Thread thread;
        public CreateSlave() {
            this.thread = new Thread(this, "CreateSlaveThread");
        }

        public void create() {
            this.thread.start();
        }

        public void run() {
            log.info("CreateSlave thread started");
            createSlaveThreads();

            /*
            After clearing pool, set `isConsumed` Flag to FALSE
             */
            container.setIsConsumed(Boolean.FALSE);
            log.info("Pool is emptied. Setting isConsumed to FALSE. Terminating Consumer!");
        }

        private void createSlaveThreads() {
            /*
            As long as there is an object in pool, eat it
             */
            int nbEmailFromPool;
            do {
                /*
                This is ensuring to take only 300 (configurable) objects at once
                 */
                List<Email> emailList = container.takeEmailListBySize(ConfigValues.numberOfEmailsToSend);
                nbEmailFromPool = emailList.size();
                log.info("Number of email objects fetch from pool: {}", nbEmailFromPool);

                /*
                If no object is remaining then don't create SLAVE
                 */
                if(nbEmailFromPool > 0) {
                    log.info("Creating EmailConsumerSlave Thread to send {} mails", emailList.size());
                    new EmailConsumerSlave(mailServer, emailList).send();
                }
            } while(nbEmailFromPool != 0);
        }
    }
}
