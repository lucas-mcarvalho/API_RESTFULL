package com.udemyCourse.course.mail;


import com.udemyCourse.course.config.EmailConfig;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@Component
public class EmailSender implements Serializable {

    Logger logger = LoggerFactory.getLogger(EmailSender.class);

    private final JavaMailSender mailSender;
    private String to;
    private String subject;
    private String body;
    private ArrayList<InternetAddress> recipientes = new ArrayList<>();
    private File attachment;



    public EmailSender (JavaMailSender mailSender){
        this.mailSender = mailSender;
    }



    public EmailSender to(String to) throws AddressException {
        this.to = to;
        this.recipientes = getRecipientes(to);
        return this;
    }

    private ArrayList<InternetAddress> getRecipientes(String to) throws AddressException {
       String toWithoutSpaces = to.replaceAll("\\s","");
        StringTokenizer tok = new StringTokenizer(toWithoutSpaces,";");
        ArrayList<InternetAddress> recipientList = new ArrayList<>();
        while(tok.hasMoreElements()){
            recipientList.add(new InternetAddress(tok.nextElement().toString()));

        }
        return recipientList;
    }

    public EmailSender withSubject(String subject) {
        this.subject = subject;
        return  this;

    }

    public EmailSender withMessage(String body) {
        this.body = body;
        return  this;
    }

    public void setRecipientes(ArrayList<InternetAddress> recipientes) {
        this.recipientes = recipientes;
    }

    public EmailSender attach(File filedir) {
        this.attachment = new File(filedir.toURI());
        return  this;

    }

    public void send(EmailConfig config) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setFrom(config.getUsername());
        helper.setTo(recipientes.toArray(new InternetAddress[0]));
        helper.setSubject(subject);
        helper.setText(body,true);
        if(attachment !=null){
            helper.addAttachment(attachment.getName(),attachment);
        }
        mailSender.send(message);
        logger.info("Email send To '%' with the subject  '%s' '%n'"+to+subject);
        reset();
    }
  public void reset(){

        this.to = null;
      this.to = null;
      this.subject = null;
      this.body = null;
      this.recipientes = null;
      this.attachment = null;

  }
}
