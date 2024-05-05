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
import tn.esprit.esprobackend.entities.Fundraiser;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.DonationRepository;
import tn.esprit.esprobackend.repositories.UserRepository;
import tn.esprit.esprobackend.services.IFundraiserService;
import tn.esprit.esprobackend.stripe.CreateCheckoutSessionRequest;
import tn.esprit.esprobackend.services.IDonationService;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final IDonationService donationService;
    private final IFundraiserService fundraiserService;
    private final ObjectMapper objectMapper;
    private final CreateCheckoutSessionRequest checkoutSessionRequest;
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;





    @GetMapping("/retrieve-all-donations")
    public List<Donation> getAllDonations() {
        List<Donation> donationList = donationService.getAllDonations();

        // Iterate through each donation and set the fundraiser name
        donationList.forEach(donation -> {
            Fundraiser fundraiser = donation.getFundraiser();
            if (fundraiser != null) {
                donation.setFundraiserName(fundraiser.getName());
            }
        });

        return donationList;
    }



//    @GetMapping("/retrieve-donation/{donation-id}")
//    public Donation getDonationById(@PathVariable("donation-id") Long donationId) {
//        Donation donation = donationService.getDonationById(donationId);
//        donation.getUser().setDonations(null);
//        return donation;
//    }


    @PostMapping("/add-donation/{fundraiserId}")
    public ResponseEntity<Donation> addDonation(@RequestBody Donation donation, @PathVariable Long fundraiserId) {
        try {
            Optional<Fundraiser> optionalFundraiser = fundraiserService.findById(fundraiserId);
            if (optionalFundraiser.isPresent()) {
                Fundraiser fundraiser = optionalFundraiser.get(); // Fetch the corresponding Fundraiser entity
                donation.setFundraiser(fundraiser); // Set the Fundraiser entity in the Donation object

                Donation savedDonation = donationService.addDonation(donation); // Save the Donation entity
                return ResponseEntity.ok(savedDonation); // Return the saved Donation entity with HTTP status 200 OK
            } else {
                return ResponseEntity.notFound().build(); // Return HTTP status 404 Not Found if Fundraiser entity is not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Return HTTP status 500 Internal Server Error if an error occurs
        }
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






