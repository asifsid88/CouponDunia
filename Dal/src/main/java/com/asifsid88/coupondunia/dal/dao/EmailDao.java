package com.asifsid88.coupondunia.dal.dao;

import com.asifsid88.coupondunia.dal.rowmapper.EmailRowMapper;
import com.asifsid88.coupondunia.dal.service.EmailService;
import com.asifsid88.coupondunia.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Calls to Database
 *
 * The below code could have been much better
 * 1. Create a Stored Procedure where you pick up the mails and move the picked up mails into Archive
 * Currently I have written it in two different SQL statements
 * 2. Currently, due to limitation (being an assignment small project and time limitation) these are written as a SQL statement
 * (not even taking care of SQL Injection thingy)
 * 3.
 */
@Service("emailService")
public class EmailDao implements EmailService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Email> getEmail() {
        /*
        Get the Emails to send
         */
        List<Email> emailList = jdbcTemplate.query(SQLQueries.getEmail(), new EmailRowMapper());

        /*
        Delete those emails from db
         */
        if(emailList.size() >0) {
            jdbcTemplate.update(SQLQueries.deleteEmailsById(getIds(emailList)));
        }

        return emailList;
    }

    public List<Email> getEmailsByIdsInBatch(Long id, int size) {
        List<Email> emailList = jdbcTemplate.query(SQLQueries.getEmailsByIdsInBatch(id, size), new EmailRowMapper());
        if(emailList.size() >0) {
            jdbcTemplate.update(SQLQueries.deleteEmailsById(getIds(emailList)));
        }

        return emailList;
    }

    public List<Email> getEmailsInBatch(int size) {
        List<Email> emailList = jdbcTemplate.query(SQLQueries.getEmailsInBatch(size), new EmailRowMapper());
        if(emailList.size() >0) {
            jdbcTemplate.update(SQLQueries.deleteEmailsById(getIds(emailList)));
        }

        return emailList;
    }

    /**
     * This could be in Utility package
     * @param emailList
     * @return ids from EmailObject
     */
    private List<Long> getIds(List<Email> emailList) {
        List<Long> ids = new LinkedList<Long>();
        for(Email email : emailList) {
            ids.add(email.getId());
        }

        return ids;
    }
}
