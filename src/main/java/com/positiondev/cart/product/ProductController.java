package com.positiondev.cart.product;

import com.positiondev.cart.cart.Cart;
import com.positiondev.cart.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired CartRepository cartRepository;

  @GetMapping("/{id}.json")
  public Product productjson(@PathVariable("id") Product product) {
    return product;
  }

  @GetMapping("/{id}")
  public ModelAndView product(@PathVariable("id") Product product) {
    Cart cart = cartRepository.findAll().iterator().next();
    ModelAndView mav = new ModelAndView("product", "product", product);
    mav.addObject("cart", cart);
    return mav;
  }
}