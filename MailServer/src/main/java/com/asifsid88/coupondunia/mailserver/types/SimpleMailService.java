package com.asifsid88.coupondunia.mailserver.types;

import com.asifsid88.coupondunia.mailserver.MailMessage;
import com.asifsid88.coupondunia.mailserver.service.MailClient;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("simpleMailService")
@Log4j2
public class SimpleMailService implements MailClient {

    @Autowired
    private MailSender mailSender;

    public void sendMail(Email email) {
        mailSender.send(MailMessage.getMailMessage(email));
        log.info("1 mail sent");
    }

    /**
     * @param emailList : List of Mails to sent
     */
    public void sendBulkMail(List<Email> emailList) {
        mailSender.send(MailMessage.getMailMessageList(emailList));
        log.info("{} mails sent", emailList.size());
    }
}
