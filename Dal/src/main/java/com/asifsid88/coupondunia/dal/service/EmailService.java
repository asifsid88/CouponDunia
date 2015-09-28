package com.asifsid88.coupondunia.dal.service;

import com.asifsid88.coupondunia.model.Email;

import java.util.List;

public interface EmailService {
    List<Email> getEmail();
    List<Email> getEmailsInBatch(int size);
    List<Email> getEmailsByIdsInBatch(Long id, int size);
}
