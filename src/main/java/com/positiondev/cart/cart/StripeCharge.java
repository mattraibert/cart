package com.positiondev.cart.cart;

import com.positiondev.cart.receipt.Receipt;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
class StripeCharge {
  @Value("${stripe.secretkey:sk_test_your_key_here}")
  private String secretKey;

  void charge(String token, Receipt receipt) {
    Stripe.apiKey = secretKey;
    Map<String, Object> params = new HashMap<>();
    params.put("amount", receipt.getTotal().getAmountCents());
    params.put("currency", receipt.getTotal().getCurrency());
    params.put("description", receipt.getDescription());
    params.put("source", token);

    try {
      Charge.create(params);
    } catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException | APIException e) {
      throw new RuntimeException(e);
    }
  }
}
