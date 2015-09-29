package com.asifsid88.coupondunia;


import com.asifsid88.coupondunia.mailserver.types.SimpleMailService;
import com.asifsid88.coupondunia.model.Email;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TODO: Complete Unit Test
 */
public class SendMailTest {

    private List<Email> emailList;
    private SimpleMailService simpleMailService;

    @Before
    public void before() {
        emailList = EmailHelper.getEmailList();
    }

    @Test
    public void sendSingleMail() {

    }
}
