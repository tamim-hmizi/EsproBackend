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
import java.util.concurrent.ConcurrentHashMap;

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

            ////////////////////////////////////////////////////////////////////////////////////////////////////////

            User user = userRepository.findById(1L).orElse(null);

            // Create and save the donation object
            Donation donation = new Donation();
            donation.setAmount(amount);
            donation.setType("Credit/Debit Card");
            donation.setStatus("Unpaid");
            donation.setUser(user);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////

            // Return the session ID and donation ID in the response
            return ResponseEntity.ok().body(Map.of("sessionId", session.getId()));
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


    @GetMapping("/api/retrieve-session/{id}")
    public ResponseEntity<?> getCheckoutSession(@PathVariable String id) {
        try {


            // Set the Stripe API key
            Stripe.apiKey = stripeSecretKey;


            // Retrieve the session
            Session session = Session.retrieve(id);
            double amountTotalInDollars = session.getAmountTotal() / 100.0;

            // Construct a custom response body with relevant session details
            String responseBody = "Session ID: " + session.getId() + "\n"
                    + "Amount Total: " + amountTotalInDollars + "\n" // Convert cents to dollars
                    + "Currency: " + session.getCurrency() + "\n"
                    + "Status: " + session.getStatus() + "\n"
                    + "Payment Status: " + session.getPaymentStatus() + "\n"
                    + "Success URL: " + session.getSuccessUrl() + "\n"
                    + "Cancel URL: " + session.getCancelUrl() + "\n"
                    + "URL: " + session.getUrl();

            // Return the custom response body
            return ResponseEntity.ok().body(responseBody);
        } catch (StripeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving checkout session: " + e.getMessage());
        }
    }







}






