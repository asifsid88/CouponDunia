package com.asifsid88.coupondunia.mailserver.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
@ComponentScan("com.asifsid88.coupondunia.mailserver")
@Log4j2
public class MailServerConfiguration {

    private SystemPropertyConfiguration systemPropertyConfiguration;

    @Autowired
    public void setSystemPropertyConfiguration(@Qualifier("mailServerEnvironment") SystemPropertyConfiguration systemPropertyConfiguration) {
        this.systemPropertyConfiguration = systemPropertyConfiguration;
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(System.getProperty(PropertyName.MAIL_HOST, PropertyDefaultValue.MAIL_HOST));

        Integer port = Integer.parseInt(System.getProperty(PropertyName.MAIL_PORT, PropertyDefaultValue.MAIL_PORT));
        javaMailSender.setPort(port);

        javaMailSender.setUsername(System.getProperty(PropertyName.MAIL_USERNAME, PropertyDefaultValue.MAIL_USERNAME));
        javaMailSender.setPassword(System.getProperty(PropertyName.MAIL_PASSWORD, PropertyDefaultValue.MAIL_PASSWORD));

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();

        log.info("in mailserver config--> {} ", System.getProperty(PropertyName.MAIL_TRANSPORT_PROTOCOL));
        properties.setProperty(PropertyName.MAIL_TRANSPORT_PROTOCOL, System.getProperty(PropertyName.MAIL_TRANSPORT_PROTOCOL, PropertyDefaultValue.MAIL_TRANSPORT_PROTOCOL));
        properties.setProperty(PropertyName.MAIL_SMTP_AUTH, System.getProperty(PropertyName.MAIL_SMTP_AUTH, PropertyDefaultValue.MAIL_SMTP_AUTH));
        properties.setProperty(PropertyName.MAIL_SMTP_STARTTLS_ENABLE, System.getProperty(PropertyName.MAIL_SMTP_STARTTLS_ENABLE, PropertyDefaultValue.MAIL_SMTP_STARTTLS_ENABLE));
        properties.setProperty(PropertyName.MAIL_DEBUG, System.getProperty(PropertyName.MAIL_DEBUG, PropertyDefaultValue.MAIL_DEBUG));

        return properties;
    }

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using MailServerConfiguration");
    }
}
