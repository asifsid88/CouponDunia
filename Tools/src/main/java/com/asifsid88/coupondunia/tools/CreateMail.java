package com.asifsid88.coupondunia.tools;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CreateMail {

    @Value("#{systemProperties['nbOfMail'] ?: 1000}")
    private int numberOfMail;

    @Value("#{systemProperties['fromEmail'] ?: 'asif.sid88@gmail.com'}")
    private String fromEmail;

    @Value("#{systemProperties['toEmail'] ?: 'asif.sid88@gmail.com'}")
    private String toEmail;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertMail() {
        try {
            jdbcTemplate.batchUpdate(sqlQuery());
            log.info("Mail created");
        } catch(Exception e) {
            log.error("Exception occurred while creating dummy mails, Exception: ", e);
        }
    }

    /*
    SQL Insert statement to fire
    The idea is to just populate the database with dummy values
     */
    private String[] sqlQuery() {
        String[] query = new String[numberOfMail];

        for(int i=0; i<numberOfMail; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO `EmailQueue` (`from_email`, `to_email`, `subject`, `body`, `status`) ")
                .append("values(")
                .append("'").append(fromEmail).append("', ")
                .append("'").append(toEmail).append("', ")
                .append("'CouponDuniaTest Mail by Asif', ")
                .append("'This is a test mail', ")
                .append("0)");

            query[i] = sb.toString();
        }

        return query;
    }
}
