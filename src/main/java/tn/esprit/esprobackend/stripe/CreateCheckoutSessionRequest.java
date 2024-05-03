package tn.esprit.esprobackend.stripe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class CreateCheckoutSessionRequest {
    @JsonProperty("amount")
    private Float amount; // Change data type to Float

    public SessionCreateParams buildSession(boolean isPayment) {
        // Ensure the minimum amount is 50 cents (500 cents)
        Long adjustedAmount = Math.max(Math.round(this.amount * 100), 500L); // Convert amount to cents

        SessionCreateParams.Builder builder = SessionCreateParams.builder()
                .setSuccessUrl("http://localhost:63849/home?succeded")
                .setCancelUrl("http://localhost:63849/donation");

        if (isPayment) {
            builder.setMode(SessionCreateParams.Mode.PAYMENT);
        } else {
            builder.setMode(SessionCreateParams.Mode.SUBSCRIPTION);
        }

        return builder
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(adjustedAmount)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Donation")
                                                                .build()
                                                )
                                                .build()
                                )
                                .setQuantity(1L)
                                .build()
                )
                .build();
    }
}
