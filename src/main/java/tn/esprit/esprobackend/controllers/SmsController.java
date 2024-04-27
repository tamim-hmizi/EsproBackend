package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.services.TwilioSmsSender;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/sendsms")
public class SmsController {


    private TwilioSmsSender smsSender;

    @GetMapping("/{to}/{message}")
    public String sendSms(@PathVariable("to") String to,@PathVariable("message") String message) {
            smsSender.sendSms(to, message);
            return "SMS sent successfully";
    }
}
