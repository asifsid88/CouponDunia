package com.asifsid88.coupondunia.mailserver.configuration;

/**
 * Properties file `keys`
 *
 * These represents the keys in properties file/Environmental variable/ System properties to be looked for
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
 *
 * If no values are supplied or there is issue while feeding Environmental values then default values are picked up from here
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
