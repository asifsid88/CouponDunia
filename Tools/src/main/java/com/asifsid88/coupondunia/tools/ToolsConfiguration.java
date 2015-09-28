package com.asifsid88.coupondunia.tools;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan("com.asifsid88.coupondunia.tools")
@Log4j2
public class ToolsConfiguration {

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using ToolsConfiguration");
    }
}
