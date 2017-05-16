package com.positiondev.cart.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;

  @GetMapping
  public List<Product> index() {
    return productRepository.findAll();
  }

  @GetMapping("/{id}")
  public Product product(@PathVariable("id") Product product) {
    return product;
  }
}