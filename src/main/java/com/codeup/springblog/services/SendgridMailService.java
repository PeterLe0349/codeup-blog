package com.codeup.springblog.services;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendgridMailService {


    private static final Logger logger = LoggerFactory.getLogger(SendgridMailService.class);

    public static void main(String[] args) throws IOException {
        Email from = new Email("test@example.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("fierem@live.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

    public String sendTextEmail() throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("add the sender email");
        String subject = "The subject";
        Email to = new Email("reciver");
        Content content = new Content("text/plain", "This is a test email");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("SENDGRID_API_KEY");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.info(response.getBody());
            return response.getBody();
        } catch (IOException ex) {
            throw ex;
        }
    }



}
