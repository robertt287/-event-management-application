package com.blue.team.event.management.application.service;

import com.blue.team.event.management.application.model.entity.EventEntity;
import com.blue.team.event.management.application.model.entity.ParticipantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public void registrationConfirmation(ParticipantEntity participant, EventEntity event) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(participant.getEmail());
        mailMessage.setSubject("Registered successfully to:" + event.getName());
        mailMessage.setText(String.format(new StringBuilder().append("Hello %s,\n")
                .append("\n")
                .append("You have been registered!\n")
                .append("\n")
                .append("Thank you,\n")
                .append("Event Management Team").toString(), participant.getFullName()));

        javaMailSender.send(mailMessage);
    }
}