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

@Import({DatabaseConfiguration.class, MailServerConfiguration.class})
@Configuration
@ComponentScan("com.asifsid88.coupondunia.core")
@Log4j2
public class CoreSystemConfiguration {

    @Autowired
    private SystemPropertyConfiguration systemPropertyConfiguration;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
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
