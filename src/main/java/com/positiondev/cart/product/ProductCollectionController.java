package com.positiondev.cart.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductCollectionController {
  @Autowired
  private ProductRepository productRepository;

  @GetMapping("/products.json")
  public Iterable<Product> indexJson() {
    return collection();
  }

  @GetMapping("/products")
  public ModelAndView index() {
    return new ModelAndView("product/index", "products", collection());
  }

  private Iterable<Product> collection() {
    return productRepository.findAll();
  }
}