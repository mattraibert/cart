package com.positiondev.cart.product;

import com.positiondev.cart.util.PersistableMoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull private String title;
  @NotNull private String description;
  @NotNull private PersistableMoney price;

  protected Product() {}

  public Product(String title, String description, PersistableMoney price) {
    this.title = title;
    this.description = description;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public PersistableMoney getPrice() {
    return price;
  }

  public void setTitle(String title) { this.title = title; }

  public void setDescription(String description) { this.description = description; }

  public void setPrice(Number price) { this.price = PersistableMoney.usd(price); }
}