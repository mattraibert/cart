package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;

import javax.persistence.*;

@Entity
public class LineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cartId", nullable = false)
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "productId", nullable = false)
  private Product product;

  @SuppressWarnings("unused")
  LineItem() {}

  public Product getProduct() {
    return product;
  }

  public LineItem(Cart cart, Product product) {
    this.cart = cart;
    this.product = product;
  }
}
