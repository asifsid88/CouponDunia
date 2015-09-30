package com.asifsid88.coupondunia.mailserver.types;

import com.asifsid88.coupondunia.mailserver.MailMessage;
import com.asifsid88.coupondunia.mailserver.service.MailClient;
import com.asifsid88.coupondunia.model.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This can be further improved, where we can keep track of failure mails
 * 1. Currently there is a flag `status` which represents whether mail is sent or not. This flag can be used to
 * indicate the mail failure so that it can be picked up in next batch/call
 * 2. We can also move the failure mails to another table, where separate batch can run to re-send such mails
 *
 * Failures can occur due to many reason, we can also track the reason for failure (have a column which tells about reason for failure)
 * Failure could occur from server end viz., "Too many mails sent", "Remote Host closed - Connection Reset" etc
 * or could occur due to malformed of MailMessage (invalid email id -to and from, subject, body or due to attachment size, etc)
 *
 * Thus having track of `reason for failure` will help us examine the exact cause of such mail failure, which can be tracked or
 * prevented in future. Thus have a separate batch running for re-sending failure message would be a better approach
 *
 */
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
