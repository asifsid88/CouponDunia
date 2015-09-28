package com.asifsid88.coupondunia.core.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

//@Import({DatabaseConfiguration.class, MailServerConfiguration.class})
@Configuration
@ComponentScan("com.asifsid88.coupondunia.core")
@Log4j2
public class CoreSystemConfiguration {

    private SystemPropertyConfiguration systemPropertyConfiguration;

    @Autowired
    public void setSystemPropertyConfiguration(@Qualifier("coreEnvironment") SystemPropertyConfiguration systemPropertyConfiguration) {
        this.systemPropertyConfiguration = systemPropertyConfiguration;
    }

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using CoreSystemConfiguration");
    }
}
