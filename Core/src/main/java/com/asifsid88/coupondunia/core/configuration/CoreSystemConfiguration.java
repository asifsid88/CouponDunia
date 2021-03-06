package com.asifsid88.coupondunia.core.configuration;

import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import com.asifsid88.coupondunia.mailserver.configuration.MailServerConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;

/**
 * CoreSystem Configuration:
 * It is dependent upon DAL (Data Access Layer) and Mail Server. Hence it is using DatabaseConfiguration and MailServerConfiguration
 *
 * It uses SystemPropertyConfiguration to load system properties which is further used by DAL and MailServer to configure themselves
 */
@Import({DatabaseConfiguration.class, MailServerConfiguration.class})
@Configuration
@ComponentScan("com.asifsid88.coupondunia.core")
@Log4j2
public class CoreSystemConfiguration {

    @Autowired
    private SystemPropertyConfiguration systemPropertyConfiguration;

    /*
    Used to create Thread using Spring
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        /*
        Configure this as per the business requirement and system configuration
         */
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using CoreSystemConfiguration");
    }
}
