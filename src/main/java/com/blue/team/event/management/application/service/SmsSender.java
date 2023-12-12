package com.blue.team.event.management.application.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsSender {

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;
    @Value("${twilio.account.sid}")
    private String twilioAccountSid;
    @Value("${twilio.from.number}")
    private String twilioFromNumber;

    public void sendSms(List<String> phoneNumbers, String messageBody) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        phoneNumbers.forEach(phoneNumber -> Message.creator(new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioFromNumber), messageBody).create());
        log.info("Sms {} was sent", messageBody );
    }
}