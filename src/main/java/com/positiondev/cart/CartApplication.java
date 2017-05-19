package com.positiondev.cart;

import com.positiondev.cart.cart.Cart;
import com.positiondev.cart.cart.CartRepository;
import com.positiondev.cart.customer.Customer;
import com.positiondev.cart.customer.CustomerRepository;
import com.positiondev.cart.product.Product;
import com.positiondev.cart.product.ProductRepository;
import com.positiondev.cart.util.PersistableMoney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CartApplication {

  private static final Logger log = LoggerFactory.getLogger(CartApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CartApplication.class);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository repository, ProductRepository productRepository, CartRepository cartRepository) {
    return (args) -> {
      // save a couple of customers
      repository.save(new Customer("Jack", "Bauer"));
      repository.save(new Customer("Chloe", "O'Brian"));
      repository.save(new Customer("Kim", "Bauer"));
      repository.save(new Customer("David", "Palmer"));
      repository.save(new Customer("Michelle", "Dessler"));

      productRepository.save(new Product("Carlisle Story", "The greated story ever about a turtle in a swimming pool", PersistableMoney.of(9.98, "USD")));
      productRepository.save(new Product("Argle Story", "Not another bargle bargle foobar", PersistableMoney.of(19.88, "USD")));
      productRepository.save(new Product("A Spring", "Spring does some things", PersistableMoney.of(9.98, "USD")));


      cartRepository.save(new Cart());
    };
  }
}