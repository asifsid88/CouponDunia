package com.asifsid88.coupondunia.tools;

import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * This is a tool to generate bulk emails and put into Database
 *
 *
 * Set System Property to configure the mails generated
 * Number of mails to generate --> nbOfMail = 1000 (default)
 * From Email --> fromEmail = asif.sid88@gmail.com (default)
 * To Email --> toEmail = asif.sid88@gmail.com (default)
 */
@Log4j2
public class CreateDummyMail {
    public static void main(String[] args) throws Exception {
        System.setProperty("nbOfMail", "1000");
        System.setProperty("fromEmail", "asif.sid88@gmail.com");
        System.setProperty("toEmail", "asif.sid88@gmail.com");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DatabaseConfiguration.class, ToolsConfiguration.class);
        log.info("CreateDummyMail is up and running !!");

        CreateMail createMail = (CreateMail) applicationContext.getBean("createMail");
        createMail.insertMail();
        log.info("{} dummy mails created !", System.getProperty("nbOfMail"));
    }
}
