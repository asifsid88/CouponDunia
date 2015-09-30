package com.asifsid88.coupondunia.tools;


import com.asifsid88.coupondunia.dal.configuration.DatabaseConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Configuration file to load ToolsConfiguration
 *
 * It is re-using DatabaseConfiguration - so we get datasource and every other configuration
 */
@Import(DatabaseConfiguration.class)
@Configuration
@ComponentScan("com.asifsid88.coupondunia.tools")
@Log4j2
public class ToolsConfiguration {

    @Autowired
    private SystemPropertyConfiguration systemPropertyConfiguration;


    @PostConstruct
    public void logEnvironment() {
        log.info("Start using ToolsConfiguration");
    }
}
