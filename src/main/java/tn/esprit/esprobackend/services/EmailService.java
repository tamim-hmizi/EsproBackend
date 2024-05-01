package tn.esprit.esprobackend.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.core.io.ClassPathResource;


import java.util.List;
import java.util.Map;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Autowired
    private PublicationServiceImpl publicationService;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendTemplatedEmail(String to, String name, String from) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setFrom(from); // Set the sender's email
        helper.setSubject("Congratulations, Top Researcher!");

        Context context = new Context();
        context.setVariable("name", name); // Set the researcher's name dynamically

        // Generate the email content from the Thymeleaf template
        String emailContent = templateEngine.process("scheduled-email", context);
        helper.setText(emailContent, true); // Send as HTML

        // Add inline image (logo)
        helper.addInline("espro", new ClassPathResource("static/espro.png")); // Path to your logo

        mailSender.send(message); // Send the email
    }

    // Scheduled task to send templated emails at specified times
    //@Scheduled(cron = "0 0 0 31 12 *") // December 31 at midnight
    @Scheduled(cron = "*/10 * * * * *") // December 31 at midnight
    public void sendEndOfYearEmail() {
        List<Map<String, Object>> topChercheurs = publicationService.getTopRDIMembers(); // Correct autowiring

        for (Map<String, Object> chercheur : topChercheurs) {
        try {
            sendTemplatedEmail((String) chercheur.get("email"), (String) chercheur.get("name"), "meriem2001ibrahim@gmail.com"); // Send the email with dynamic content
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle MessagingException
        }}
    }
}
