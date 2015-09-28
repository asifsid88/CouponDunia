package com.asifsid88.coupondunia.core;

import com.asifsid88.coupondunia.core.configuration.CoreSystemConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunCouponDuniaSystem {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CoreSystemConfiguration.class);
    }
}
