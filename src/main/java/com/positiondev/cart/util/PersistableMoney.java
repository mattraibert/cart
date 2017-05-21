package com.positiondev.cart.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class PersistableMoney implements Serializable {
  private BigDecimal amount;

  @Column(length = 3, nullable = false)
  private String currency;

  @SuppressWarnings("unused")
  private PersistableMoney() {}

  public PersistableMoney(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public String getDisplay() {return "$" + amount; }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public static PersistableMoney of(Number amount, String currency) {
    return of(new BigDecimal(amount.toString()), currency);
  }

  private static PersistableMoney of(BigDecimal amount, String currency) {
    return new PersistableMoney(amount, currency);
  }

  public static PersistableMoney usd(Number amount) {return of(amount, "USD");}
}
