package com.project.uber.UberApp.services;

public interface EmailSenderService {

    public void SendEmail(String toEmail, String subject, String body);
    void SendEmail(String toEmail[], String subject, String body);

}
