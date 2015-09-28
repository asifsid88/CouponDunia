package com.asifsid88.coupondunia.core;

import com.asifsid88.coupondunia.core.configuration.CoreSystemConfiguration;
import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import com.asifsid88.coupondunia.mailserver.configuration.MailServerConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunCouponDuniaSystem {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CoreSystemConfiguration.class, MailServerConfiguration.class, DatabaseConfiguration.class);
        System.out.println("Data Access Layer Up and running !!");
    }
}
