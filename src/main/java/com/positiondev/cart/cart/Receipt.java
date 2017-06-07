package com.positiondev.cart.cart;

import com.positiondev.cart.util.PersistableMoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Receipt {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private PersistableMoney total;
  private String description;

  public Receipt(PersistableMoney total, String description) {
    this.total = total;
    this.description = description;
  }

  public PersistableMoney getTotal() {
    return total;
  }

  public String getDescription() {
    return description;
  }
}
