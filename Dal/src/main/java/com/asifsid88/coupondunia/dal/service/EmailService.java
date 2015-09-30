package com.asifsid88.coupondunia.dal.service;

import com.asifsid88.coupondunia.model.Email;

import java.util.List;

/**
 * Email service interface, giving features to get data from database,
 * It can have many implementations
 *
 *
 * Imagine a case: Where we want to migrate from JDBCTemplate to ORM, then rather than removing EmailDao (for example) completely,
 * we can have another implementation of EmailService say `HibernateEmailDao` and then we can run AB test for older and newer version
 * and based on zero-delta we can easily migrate our database system
 */
public interface EmailService {
    List<Email> getEmail();
    List<Email> getEmailsInBatch(int size);
    List<Email> getEmailsByIdsInBatch(Long id, int size);
}
