package tn.esprit.esprobackend.controllers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.services.EmailService;

@RestController
@AllArgsConstructor
@RequestMapping("/send-email")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailRestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/{to}/{subject}/{text}")
    public String sendEmail(@PathVariable("to") String to,@PathVariable("subject") String subject,@PathVariable("text") String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return "Email sent successfully!";
    }
}
