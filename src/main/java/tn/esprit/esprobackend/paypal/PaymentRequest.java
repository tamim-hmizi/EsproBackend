package tn.esprit.esprobackend.paypal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private String method;
    private String amount;
    private String currency;
    private String description;

    // Getters and setters
}

