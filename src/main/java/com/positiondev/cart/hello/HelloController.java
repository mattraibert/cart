package com.positiondev.cart.hello;

import com.positiondev.cart.product.Product;
import com.positiondev.cart.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

  @Autowired
  private
  ProductRepository productRepository;

  @RequestMapping("/hello")
  public String admin() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/products")
  public List<Product> index() {
    return productRepository.findAll();
  }

  @RequestMapping("/products/{id}")
  public Product product(@PathVariable("id") Product product) {
    return product;
  }
}