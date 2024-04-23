package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.services.TwilioSmsSender;
@RestController
@AllArgsConstructor
@RequestMapping("/send-sms")
public class SmsController {

    @Autowired
    private TwilioSmsSender smsSender;

    @PostMapping("/{to}/{message}")
    public String sendSms(@PathVariable("to") String to,@PathVariable("message") String message) {
        smsSender.sendSms(to, message);
        return "SMS sent successfully";
    }
}
