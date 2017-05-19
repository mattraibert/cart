package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/carts")
public class CartController {
  @Autowired
  private CartRepository cartController;

  @GetMapping("/{id}/product/{productId}")
  public RedirectView addProduct(@PathVariable("id") Cart cart, @PathVariable("productId") Product product) {
    cart.addProduct(product);
    cartController.save(cart);
    return new RedirectView("/carts/" + cart.getId() + ".json");
  }


//  @GetMapping("/redirectWithRedirectView")
//  public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
//    attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
//    attributes.addAttribute("attribute", "redirectWithRedirectView");
//  }

  @GetMapping("/{id}.json")
  public Cart cartjson(@PathVariable("id") Cart cart) {
    return cart;
  }

  @GetMapping("/{id}")
  public ModelAndView cart(@PathVariable("id") Cart cart) {
    return new ModelAndView("cart", "cart", cart);
  }
}