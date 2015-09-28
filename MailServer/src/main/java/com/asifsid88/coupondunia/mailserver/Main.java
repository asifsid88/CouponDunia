package com.asifsid88.coupondunia.mailserver;

import com.asifsid88.coupondunia.mailserver.configuration.MailServerConfiguration;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MailServerConfiguration.class);
        System.out.println("Mail Server Configuration is up and running !!");

        MailServerDelegate mailServerDelegate = (MailServerDelegate) applicationContext.getBean(MailServerDelegate.class);
        //mailServerDelegate.sendBulkMail(Collections.EMPTY_LIST);

        Email email = new Email();
        email.setBody(" this is just for testing!! send from main class");
        email.setFromEmail("asif.sid88@gmail.com");
        email.setToEmail("asif.sid88@gmail.com");
        email.setSubject("Test mail ! hogaya re ! :D");
        mailServerDelegate.sendMail(email);
    }
}