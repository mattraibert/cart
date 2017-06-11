package com.positiondev.cart.util;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class PersistableMoney implements Serializable {
  @NotNull private BigDecimal amount;

  @Column(length = 3, nullable = false)
  @NotNull
  private String currency;

  @SuppressWarnings("unused")
  private PersistableMoney() {}

  public PersistableMoney(BigDecimal amount, String currency) {
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

  public static PersistableMoney sum(PersistableMoney... moneys) {
    return sum(Arrays.asList(moneys));
  }

  public static PersistableMoney sum(Collection<PersistableMoney> moneys) {
    List<String> currencies = moneys.stream().map(PersistableMoney::getCurrency).distinct().collect(Collectors.toList());
    if (currencies.size() > 1)
      throw new IllegalArgumentException("Currencies must match");
    if (currencies.isEmpty())
      throw new IllegalArgumentException("Must have something to sum");

    BigDecimal sum = moneys.stream().map(PersistableMoney::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    return new PersistableMoney(sum, currencies.get(0));
  }

  @NotNull
  public static PersistableMoney usd(Number amount) {
    return new PersistableMoney(new BigDecimal(amount.toString()), "USD");
  }

  @NotNull
  public static PersistableMoney of(Number amount, String currency) {
    return new PersistableMoney(new BigDecimal(amount.toString()), currency);
  }

  public int getAmountCents() {
    return getAmount().multiply(BigDecimal.valueOf(100)).intValue();
  }

  public boolean isZero() {
    return getAmountCents() <= 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PersistableMoney that = (PersistableMoney) o;

    return amount.equals(that.amount) && currency.equals(that.currency);
  }

  @Override
  public int hashCode() {
    int result = amount.hashCode();
    result = 31 * result + currency.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "PersistableMoney{" +
        "amount=" + amount +
        ", currency='" + currency + '\'' +
        '}';
  }
}
