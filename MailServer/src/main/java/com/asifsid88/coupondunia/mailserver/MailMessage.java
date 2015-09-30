package com.asifsid88.coupondunia.mailserver;

import com.asifsid88.coupondunia.model.Email;
import org.springframework.mail.SimpleMailMessage;

import java.util.LinkedList;
import java.util.List;

/**
 * A helper class used to create a MailMessage Object
 * This can be enhanced further to use strategy pattern so that based on different types of Mails (or objects) we need to sent
 * we can have different strategies (say templates)
 */
public class MailMessage {

    public static SimpleMailMessage[] getMailMessageList(List<Email> emailList) {
        List<SimpleMailMessage> simpleMailMessageList = new LinkedList<SimpleMailMessage>();

        for(Email email : emailList) {
            simpleMailMessageList.add(getMailMessage(email));
        }

        SimpleMailMessage[] simpleMailMessages = new SimpleMailMessage[0];
        if(simpleMailMessageList.size() != 0) {
            simpleMailMessages = simpleMailMessageList.toArray(simpleMailMessages);
        }

        return simpleMailMessages;
    }

    public static SimpleMailMessage getMailMessage(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(email.getFromEmail());
        mailMessage.setTo(email.getToEmail().split(";"));
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(email.getBody());

        return mailMessage;
    }
}
