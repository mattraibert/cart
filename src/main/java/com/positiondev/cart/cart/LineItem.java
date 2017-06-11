package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class LineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull @ManyToOne(optional = false) private Cart cart;
  @NotNull @ManyToOne(optional = false) private Product product;

  @SuppressWarnings("unused") private LineItem() {}

  public Product getProduct() {
    return product;
  }

  public LineItem(Cart cart, Product product) {
    this.cart = cart;
    this.product = product;
  }

  public Long getId() {
    return id;
  }

  public Cart getCart() {
    return cart;
  }
}
