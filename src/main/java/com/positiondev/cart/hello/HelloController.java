package com.positiondev.cart.hello;

import com.google.common.collect.Iterables;
import com.positiondev.cart.customer.Customer;
import com.positiondev.cart.customer.CustomerRepository;
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