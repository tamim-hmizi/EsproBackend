package tn.esprit.esprobackend.controllers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.esprobackend.services.EmailService;

@RestController
@AllArgsConstructor
@RequestMapping("/send-email")
public class EmailRestController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/{to}/{subject}/{text}")
    public String sendEmail(@PathVariable("to") String to,@PathVariable("subject") String subject,@PathVariable("text") String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return "Email sent successfully!";
    }
}
