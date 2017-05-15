package com.position.cart.hello;

import com.google.common.collect.Iterables;
import com.position.cart.Customer;
import com.position.cart.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  private
  CustomerRepository customerRepository;

  @RequestMapping("/hello")
  public String admin() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/")
  public String index() {
    return String.join("<hr />", Iterables.transform(customerRepository.findAll(), Customer::toString));
  }
}