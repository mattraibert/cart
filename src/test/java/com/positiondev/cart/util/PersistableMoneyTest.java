package com.positiondev.cart.util;

import org.junit.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PersistableMoneyTest {
  @Test
  public void shouldBeAbleToSumUsds() {
    assertEquals(PersistableMoney.usd(150), PersistableMoney.sum(PersistableMoney.usd(99), PersistableMoney.usd(51)));
  }

  @Test
  public void shouldReturnSumInCurrencyOfAddends() {
    PersistableMoney sum = PersistableMoney.sum(PersistableMoney.of(99, "GBP"), PersistableMoney.of(51, "GBP"));
    assertEquals(PersistableMoney.of(150, "GBP"), sum);
  }

  @Test
  public void shouldNotSumMixedCurrencies() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      PersistableMoney.sum(PersistableMoney.usd(99), PersistableMoney.of(51, "GBP"));
    });
    assertEquals("Currencies must match", exception.getMessage());
  }

  @Test
  public void shouldSomethingEmpty() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      PersistableMoney.sum(Collections.emptyList());
    });
    assertEquals("Must have something to sum", exception.getMessage());
  }
}