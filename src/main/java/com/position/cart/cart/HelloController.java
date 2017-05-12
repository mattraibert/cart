package com.position.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  @Autowired
  CustomerRepository customerRepository;

  @RequestMapping("/")
  public String index() {
    return String.valueOf(customerRepository.findAll());
  }
}