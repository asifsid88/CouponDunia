package com.asifsid88.coupondunia.core;

import com.asifsid88.coupondunia.core.configuration.CoreSystemConfiguration;
import com.asifsid88.coupondunia.core.producer.EmailProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * We can export this module as a `fat-jar` so that we can ship it to any place and run the system from there
 *
 * What it does?
 * It uses DAL to read mails from Database and uses MailServer to send mails
 * As soon as the system loads up, it creates a Producer thread- which is responsible for getting mails from DB
 * in intervals (configurable interval value) and put them into a container (EmailContainer)
 * As there is a new value available in Container, then Consumer will start and it will create as many thread as
 * per the values in container and start sending mails
 *
 * Producer uses DAL to get mails from DB
 * Consumer uses MailServer to send mails
 *
 * Consumer is created as a Master/ Slave configuration where master only master is registered to listen the change in
 * container. And as Master finds a new value in it, it orders slaves to sent those values (in parallel threads)
 * As the work is done, these slaves are destroyed
 * As many slaves are created as required (Number of slaves created depends upon container size and number of mails
 * each slaves can send/handle -- both these values are configurable)
 *
 * Producer and Consumer (Master) registers themselves to Container -- Observer Design pattern is used for communication
 *
 * Producer is a continuous running thread, which looks for new values in DB. As it finds it populates the container
 * Consumer(Master) is not a thread and it is triggered only when there is a new value available. Then this Consumer(Master)
 * creates as many thread as required to sent (empty container) mails depending upon configuration
 *
 * NOTE: Through-out the system, exception could have been better handled. System should have custom exceptions based on our
 * requirements
 */
@Log4j2
public class RunCouponDuniaSystem {
    public static void main(String[] args) throws Exception {
        /*
        Loads up the configuration
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CoreSystemConfiguration.class);
        log.info("CouponDunia System loaded");

        /**
         * Start producer thread, to get mail from database
         */
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext.getBean("taskExecutor");
        EmailProducer emailProducer = (EmailProducer) applicationContext.getBean("emailProducer");
        taskExecutor.execute(emailProducer);
    }
}
