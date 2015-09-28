package com.asifsid88.coupondunia.configuration;

import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import com.asifsid88.coupondunia.mailserver.configuration.MailServerConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Import({DatabaseConfiguration.class, MailServerConfiguration.class})
@Configuration
@ComponentScan({"com.asifsid88.coupondunia.resource", "com.asifsid88.coupondunia"})
@Log4j2
public class MailWebServiceConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using MailWebServiceConfiguration");
    }
}
