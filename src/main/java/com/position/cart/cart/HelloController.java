package com.position.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    Stream<Customer> customerStream = customerRepository.findAll().stream();
    return String.join("<hr />", customerStream.map(Customer::toString).collect(Collectors.toList()));
  }
}