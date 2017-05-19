package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @OneToMany(cascade=ALL, mappedBy="cart")
  private List<LineItem> lineItems;

  public Cart() {
  }

  public void addProduct(Product product) {
    lineItems.add(new LineItem(this, product));
  }

  public Long getId() {
    return id;
  }

  public List<LineItem> getLineItems() {
    return lineItems;
  }
}