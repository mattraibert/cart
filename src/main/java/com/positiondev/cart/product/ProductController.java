package com.positiondev.cart.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/products")
public class ProductController {
  @GetMapping("/{id}.json")
  public Product productjson(@PathVariable("id") Product product) {
    return product;
  }

  @GetMapping("/{id}")
  public ModelAndView product(@PathVariable("id") Product product) {
    return new ModelAndView("product", "product", product);
  }
}