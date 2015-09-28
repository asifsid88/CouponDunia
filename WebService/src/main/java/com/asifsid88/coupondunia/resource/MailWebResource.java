package com.asifsid88.coupondunia.resource;

import com.asifsid88.coupondunia.*;
import com.asifsid88.coupondunia.dal.DALServiceDelegate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Controller
@Log4j2
public class MailWebResource {

    @Autowired
    private DALServiceDelegate dalServiceDelegate;

    @Autowired
    private EmailSender emailSender;

    @GET
    @RequestMapping("/coupondunia/sendmail")
    @Produces(MediaType.APPLICATION_JSON)
    public ModelAndView sendMail() {
        // start a thread to fill EmailContainer (picking from database)
        new EmailProducer(dalServiceDelegate).start();
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            log.error("Exception while waiting for EmailProducer to populate something. Exception: {}", e);
        }
        Response response = sendBulkMail();
        log.info("Received response and updating view");
        return getModelAndView(ViewName.SEND_MAIL, response);
    }

    @GET
    @RequestMapping("/coupondunia/pollmail")
    @Produces(MediaType.APPLICATION_JSON)
    public ModelAndView pollMail() {
        Response response = sendBulkMail();

        return getModelAndView(ViewName.SEND_MAIL , response);
    }

    private Response sendSingleMail() {
        Response response = new Response();
        int mailSentCount = emailSender.sendMail();
        response.setMailCount(mailSentCount);
        response.setStatus(Status.COMPLETE.name());

        return response;
    }

    private Response sendBulkMail() {
        Response response = new Response();
        int mailSentCount = emailSender.sendBulkMail();
        response.setMailCount(mailSentCount);
        if(mailSentCount == 0) {
            response.setStatus(Status.COMPLETE.name());
        } else {
            response.setStatus(Status.INPROGRESS.name());
        }

        return response;
    }

    private ModelAndView getModelAndView(String viewName, Response response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        modelAndView.addObject("data", response);

        return modelAndView;
    }
}
