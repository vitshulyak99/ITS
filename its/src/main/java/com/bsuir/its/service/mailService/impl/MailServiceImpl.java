package com.bsuir.its.service.mailService.impl;

import com.bsuir.its.service.mailService.MailService;
import com.bsuir.its.service.mailService.config.MailSenderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final MailSenderConfig mailSenderConfig;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender, MailSenderConfig mailSenderConfig) {
        this.javaMailSender = javaMailSender;
        this.mailSenderConfig = mailSenderConfig;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachments(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        javaMailSender.send(message);
    }
}
