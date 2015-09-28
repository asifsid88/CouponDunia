package com.asifsid88.coupondunia.mailserver.configuration;

/**
 * Properties file `keys`
 */
interface PropertyName {
    String MAIL_HOST = "mail.host";
    String MAIL_PORT = "mail.port";
    String MAIL_USERNAME = "mail.username";
    String MAIL_PASSWORD = "mail.password";
    String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    String MAIL_SMTP_AUTH = "mail.smtp.auth";
    String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    String MAIL_DEBUG = "mail.debug";
}

/**
 * DEFAULT Properties value
 */
interface PropertyDefaultValue {
    String MAIL_HOST = "smtp.gmail.com";
    String MAIL_PORT = "587";
    String MAIL_USERNAME = "asif.couponduniatest@gmail.com";
    String MAIL_PASSWORD = "coupondu";
    String MAIL_TRANSPORT_PROTOCOL = "smtp";
    String MAIL_SMTP_AUTH = "true";
    String MAIL_SMTP_STARTTLS_ENABLE = "true";
    String MAIL_DEBUG = "false";
}
