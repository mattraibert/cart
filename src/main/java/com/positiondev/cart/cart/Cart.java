package com.positiondev.cart.cart;

import com.google.common.collect.Lists;
import com.positiondev.cart.product.Product;
import com.positiondev.cart.util.PersistableMoney;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToMany(cascade = ALL, mappedBy = "cart")
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

  public PersistableMoney getTotal() {
    return PersistableMoney.usd(0);
  }

  public List<Product> getProducts() {
    return Lists.transform(lineItems, LineItem::getProduct);
  }
}