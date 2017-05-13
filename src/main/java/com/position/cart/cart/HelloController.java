package com.position.cart.cart;

import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  private
  CustomerRepository customerRepository;

  @RequestMapping("/admin")
  public String admin() {
    return "admin";
  }

  @RequestMapping("/")
  public String index() {
    return String.join("<hr />", Iterables.transform(customerRepository.findAll(), Customer::toString));
  }
}