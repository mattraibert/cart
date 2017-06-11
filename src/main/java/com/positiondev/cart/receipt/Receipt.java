package com.positiondev.cart.receipt;

import com.positiondev.cart.util.PersistableMoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Receipt {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull private PersistableMoney total;
  @NotNull private String description;

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
