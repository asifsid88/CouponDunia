package com.asifsid88.coupondunia;


import com.asifsid88.coupondunia.mailserver.types.SimpleMailService;
import com.asifsid88.coupondunia.model.Email;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SendMailTest {

    private List<Email> emailList;
    private SimpleMailService simpleMailService;

    @Before
    public void setUp() {
        emailList = EmailHelper.getEmailList(10);
    }

    @Test
    public void sendSingleMail() {

    }
}
