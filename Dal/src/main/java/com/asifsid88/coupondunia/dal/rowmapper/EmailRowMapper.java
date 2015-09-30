package com.asifsid88.coupondunia.dal.rowmapper;

import com.asifsid88.coupondunia.model.Email;
import com.asifsid88.coupondunia.dal.rowmapper.property.EmailProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMappers are responsible for translating/ converting Database Table into Objects
 * Email represents `emailqueue` table in database. This can be also considered as the `select part in sql queriesz
 */
@Log4j2
public class EmailRowMapper implements RowMapper<Email> {
    public Email mapRow(ResultSet resultSet, int i) throws SQLException {

        Email email = new Email();
        try {
            email.setId(resultSet.getLong(EmailProperty.ID));
            email.setFromEmail(resultSet.getString(EmailProperty.FROM_EMAIL));
            email.setToEmail(resultSet.getString(EmailProperty.TO_EMAIL));
            email.setBody(resultSet.getString(EmailProperty.BODY));
            email.setSubject(resultSet.getString(EmailProperty.SUBJECT));
            email.setStatus(resultSet.getBoolean(EmailProperty.STATUS));
        } catch(Exception e) {
            log.error("Exception occurred while fetching Email Object. Exception: {}", e);
        }

        return email;
    }
}
