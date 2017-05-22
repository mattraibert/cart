package com.positiondev.cart.product;

import com.positiondev.cart.cart.Cart;
import com.positiondev.cart.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired CartRepository cartRepository;
  @Autowired ProductRepository productRepository;

  @GetMapping("/new")
  public ModelAndView newProduct() {
    return new ModelAndView("product/new", "product", new Product());
  }

  @PostMapping("/new")
  public RedirectView createProduct(@ModelAttribute Product product) {
    productRepository.save(product);
    return new RedirectView("/products");
  }

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