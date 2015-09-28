package com.asifsid88.coupondunia.dal;

import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        System.out.println("Data Access Layer Up and running !!");
    }
}
