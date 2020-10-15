package com.bsuir.its.service.mailService;

import javax.mail.MessagingException;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendMessageWithAttachments(String to, String subject, String text,String pathToAttachment) throws MessagingException;
}
