package tn.esprit.esprobackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Donation;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.DonationRepository;
import tn.esprit.esprobackend.repositories.UserRepository;
import tn.esprit.esprobackend.stripe.CreateCheckoutSessionRequest;
import tn.esprit.esprobackend.services.IDonationService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final IDonationService donationService;
    private final ObjectMapper objectMapper;
    private final CreateCheckoutSessionRequest checkoutSessionRequest;
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;



    @GetMapping("/retrieve-all-donations")
    public List<Donation> getAllDonations() {
        List<Donation> donationList = donationService.getAllDonations();
        donationList.forEach(donation -> donation.getUser().setDonations(null));
        return donationList;
    }

    @GetMapping("/retrieve-donation/{donation-id}")
    public Donation getDonationById(@PathVariable("donation-id") Long donationId) {
        Donation donation = donationService.getDonationById(donationId);
        donation.getUser().setDonations(null);
        return donation;
    }


    @PostMapping("/add-donation")
    public Donation addModule(@RequestBody Donation d) {
        Donation donation = donationService.addDonation(d);
        return donation;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostMapping("/api/create-checkout-session")
    public ResponseEntity<?> createCheckoutSession(@RequestBody Map<String, String> requestData) {
        Stripe.apiKey = stripeSecretKey;

        try {
            String amountStr = requestData.get("amount");
            if (amountStr == null || amountStr.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Amount is required.");
            }

            Float amount = Float.parseFloat(amountStr);
            CreateCheckoutSessionRequest request = new CreateCheckoutSessionRequest();
            request.setAmount(amount);

            SessionCreateParams sessionParams = request.buildSession(true);
            Session session = Session.create(sessionParams);

            User user = userRepository.findById(1L).orElse(null);

            // Create and save the donation object
            Donation donation = new Donation();
            donation.setAmount(amount);
            donation.setType("Credit/Debit Card");
            donation.setStatus("Inomplete");
            donation.setUser(user);

            Donation savedDonation = donationService.addDonation(donation);

            // Return the session ID and donation ID in the response
            return ResponseEntity.ok().body(Map.of("sessionId", session.getId(), "donationId", savedDonation.getId()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid amount format: " + e.getMessage());
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating checkout session: " + e.getMessage());
        }
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @PostMapping("/api/update-status")
    public ResponseEntity<String> updateDonationStatus(@RequestBody Map<String, String> requestData) {
        String donationId = requestData.get("donationId");

        try {
            // Fetch donation by ID
            Donation donation = donationRepository.findById(Long.parseLong(donationId)).orElse(null);
            if (donation != null) {
                // Update donation status
                donation.setStatus("Complete");
                donationService.updateDonation(donation);
                return ResponseEntity.ok("Donation status updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Donation not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating donation status.");
        }
    }




}






