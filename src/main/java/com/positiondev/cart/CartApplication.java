package com.positiondev.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:secret/secret.properties", ignoreResourceNotFound = true)
public class CartApplication {
  private static final Logger log = LoggerFactory.getLogger(CartApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CartApplication.class);
  }

//  @Bean
//  public CommandLineRunner demo(CustomerRepository repository, ProductRepository productRepository, CartRepository cartRepository) {
//    return (args) -> {
//      // save a couple of customers
//      repository.save(new Customer("Jack", "Bauer"));
//      repository.save(new Customer("Chloe", "O'Brian"));
//      repository.save(new Customer("Kim", "Bauer"));
//      repository.save(new Customer("David", "Palmer"));
//      repository.save(new Customer("Michelle", "Dessler"));
//
//      productRepository.save(new Product("Carlisle Story", "The greated story ever about a turtle in a swimming pool", PersistableMoney
//          .usd(9.98)));
//      productRepository.save(new Product("Argle Story", "Not another bargle bargle foobar", PersistableMoney.usd(19.88)));
//      productRepository.save(new Product("A Spring", "Spring does some things", PersistableMoney.usd(9.98)));
//
//      cartRepository.save(new Cart());
//    };
//  }
}