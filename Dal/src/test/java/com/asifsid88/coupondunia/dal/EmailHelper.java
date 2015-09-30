package com.asifsid88.coupondunia.dal;

import com.asifsid88.coupondunia.model.Email;

import java.util.LinkedList;
import java.util.List;

public final class EmailHelper {

    public static List<Email> getEmailList(int n) {
        List<Email> emailList = new LinkedList<Email>();

        for(int i=0; i<n; ++i) {
            emailList.add(getEmail());
        }

        return emailList;
    }

    public static Email getEmail() {
        Email email = new Email();
        email.setBody("testing");
        email.setFromEmail("asif.sid88@gmail.com");
        email.setToEmail("asif.sid88@gmail.com");
        email.setSubject("test");

        return email;
    }

}
