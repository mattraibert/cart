package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartRepository cartRepository;
  @Value("${stripe.secretkey:sk_test_your_key_here}")
  private String secretKey;

  @PostMapping("/{id}")
  public RedirectView addProduct(@PathVariable("id") Cart cart, @RequestParam(value = "productId") Product product) {
    cart.addProduct(product);
    cartRepository.save(cart);
    return new RedirectView("/carts/" + cart.getId());
  }

  @GetMapping("/{id}.json")
  public Cart cartjson(@PathVariable("id") Cart cart) {
    return cart;
  }

  @GetMapping("/{id}")
  public ModelAndView cart(@PathVariable("id") Cart cart) {
    return new ModelAndView("cart", "cart", cart);
  }

  @GetMapping("/{id}/review")
  public ModelAndView reviewCart(@PathVariable("id") Cart cart) {
    return new ModelAndView("cart/review", "cart", cart);
  }

  @PostMapping("/{id}/charge")
  public RedirectView chargeCart(@PathVariable("id") Cart cart,
                                 @RequestParam(value = "stripeToken") String token) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
    Stripe.apiKey = secretKey;

    Map<String, Object> params = new HashMap<>();
    int amountCents = cart.getTotal().getAmountCents();
    if (amountCents > 0) {
      params.put("amount", amountCents);
      params.put("currency", cart.getTotal().getCurrency());
      params.put("description", "Position Cart #" + cart.getId());
      params.put("source", token);

      Charge charge = Charge.create(params);
    }
    return new RedirectView("/products");
  }
}