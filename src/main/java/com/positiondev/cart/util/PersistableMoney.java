package com.positiondev.cart.util;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

@Embeddable
public class PersistableMoney implements Serializable {
  private BigDecimal amount;

  @Column(length = 3, nullable = false)
  private String currency;

  @SuppressWarnings("unused")
  private PersistableMoney() {}

  private PersistableMoney(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public BigDecimal getAmountUsd() {return amount;}

  public String getDisplay() {return "$" + amount; }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public static PersistableMoney sum(Collection<PersistableMoney> moneys) {
    return usd(moneys.stream().map(PersistableMoney::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
  }

  @NotNull
  public static PersistableMoney usd(Number amount) {
    return new PersistableMoney(new BigDecimal(amount.toString()), "USD");
  }

  public int getAmountCents() {
    return getAmount().multiply(BigDecimal.valueOf(100)).intValue();
  }
}
