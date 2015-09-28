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
 */
@Component
@Log4j2
public class EmailContainer {

    private BlockingQueue<Email> emailPool = new LinkedBlockingDeque<Email>();

    public void putEmail(Email email) {
        if(email != null) {
            emailPool.add(email);
        }
    }

    public void putEmailList(List<Email> emailList) {
        if(emailList!=null && emailList.size()>0) {
            emailPool.addAll(emailList);
        }
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
}
