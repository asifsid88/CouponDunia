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
        try {
            mailSender.send(MailMessage.getMailMessage(email));
            log.info("1 mail sent");
        } catch(Exception e) {
            log.error("Exception occurred while sending single mail: Exception: ", e);
        }
    }

    /**
     * @param emailList : List of Mails to sent
     */
    public void sendBulkMail(List<Email> emailList) {
        try {
            mailSender.send(MailMessage.getMailMessageList(emailList));
            log.info("{} mails sent", emailList.size());
        } catch(Exception e) {
            log.error("Exception occurred while sending {} mails: Exception: ", emailList.size(), e);
        }
    }
}
