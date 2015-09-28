package com.asifsid88.coupondunia.mailserver.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * This is responsible for loading up system configuration based on environment
 * Name of file should be environment name
 * -Denv=dev   will pick up properties/dev.properties file (i.e., loading dev environment)
 */
@Component("mailServerEnvironment")
@Log4j2
public class SystemPropertyConfiguration {
    @Value("#{systemProperties['env'] ?: 'dev'}")
    private String environment;

    @Bean
    public SystemPropertyConfiguration setSystemPropertyConfiguration() {
        log.info("Loading Environment:{} ", environment);
        final Properties systemProperties = System.getProperties();
        String fileName = "properties/" + environment + ".properties";

        try {
            log.info("Loading properties file:{} ", fileName);
            systemProperties.load(getClass().getClassLoader().getResourceAsStream(fileName));
            log.info("System property loaded for Environment:{}", environment);
        } catch (Exception e) {
            log.error("Failed to load {}.properties file", environment, e);
        }

        return this;
    }
}
