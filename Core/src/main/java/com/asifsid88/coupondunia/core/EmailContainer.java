package com.asifsid88.coupondunia.core;

import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Email Container: Where email will be populated from DB and will be consumed by Mail Server
 *
 * This container can be made more Generic, by passing `raw-type` to it. But for sake of brevity it is made
 * specifically `Email` object container
 */
@Component
@Log4j2
public class EmailContainer {

    /*
    BlockingQueue is used to ensure `concurrency`
     */
    private BlockingQueue<Email> emailPool = new LinkedBlockingDeque<Email>();

    /*
    Whenever producer adds in pool, it will check whether consumer is already consuming existing mails
    If not then, it will ask container to notify consumer
     */
    private boolean isConsumed;

    private List<EmailContainerObserver> containerObserverList = new LinkedList<EmailContainerObserver>();

    public void register(EmailContainerObserver observer) {
        containerObserverList.add(observer);
    }

    public void setIsConsumed(boolean isConsumed) {
        this.isConsumed = isConsumed;
    }

    public boolean isConsumed() {
        return this.isConsumed;
    }

    public int poolSize() {
        return emailPool.size();
    }

    public void putEmail(Email email, EmailContainerObserver observer) {
        if(email != null) {
            emailPool.add(email);
        }
        /*
        New value is available in pool. Notify consumers
         */
        notifyObservers(observer);
    }

    public void putEmailList(List<Email> emailList, EmailContainerObserver observer) {
        if(emailList!=null && emailList.size()>0) {
            emailPool.addAll(emailList);
        }
        /*
        New value is available in pool. Notify consumers
         */
        notifyObservers(observer);
    }

    /**
     * Blocking call -- waits for specified time
     * Picks 1 email from the Pool
     * @return Email obtained from pool
     */
    public Email takeEmail() {
        Email email = null;
        try {
            email = emailPool.poll(ConfigValues.waitForEmailInPoolToArrive, TimeUnit.SECONDS);
        } catch(Exception e) {
            log.error("Exception occurred while getting data from EmailPool, Exception: {}", e);
        }

        return email;
    }

    /**
     * Non-blocking call, tries to get at most `size` emails from pool
     * @param size Number of emails expected from Pool
     * @return List of emails of at most `size` from pool
     */
    public List<Email> takeEmailListBySize(int size) {
        List<Email> emailList = new LinkedList<Email>();
        emailPool.drainTo(emailList, size);

        return emailList;
    }

    /**
     * Non-blocking call, tries to get at most `numberOfEmailsToSend` (default) emails from pool
     * @return List of emails of at most `default` size
     */
    public List<Email> takeEmailList() {
        return takeEmailListBySize(ConfigValues.numberOfEmailsToSend);
    }

    /*
    Notify consumers to take value from pool
     */
    private void notifyObservers(EmailContainerObserver observer) {
        /*
        Check whether consumer is also feeding from pool
         */
        if(!isConsumed()) {
            for(EmailContainerObserver containerObserver : containerObserverList) {
                /*
                Notify all the consumers but one which invoked (producer)
                 */
                if(!observer.getName().equals(containerObserver.getName())) {
                    containerObserver.trigger();
                }
            }
        }
    }
}
