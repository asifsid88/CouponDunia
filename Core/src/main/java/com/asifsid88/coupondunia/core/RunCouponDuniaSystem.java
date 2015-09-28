package com.asifsid88.coupondunia.core;

import com.asifsid88.coupondunia.core.configuration.CoreSystemConfiguration;
import com.asifsid88.coupondunia.core.producer.EmailProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Log4j2
public class RunCouponDuniaSystem {
    public static void main(String[] args) throws Exception {
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
