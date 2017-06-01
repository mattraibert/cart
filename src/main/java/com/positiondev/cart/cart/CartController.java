package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private StripeCharge stripeCharge;
  @Autowired
  private CartRepository cartRepository;

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
  public RedirectView chargeCart(@PathVariable("id") Cart cart, @RequestParam(value = "stripeToken") String token) {
    if (!cart.getTotal().isZero()) {
      stripeCharge.charge(token, cart);
    }
    return new RedirectView("/products");
  }
}