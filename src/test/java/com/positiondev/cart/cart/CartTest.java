package com.positiondev.cart.cart;

import com.positiondev.cart.product.Product;
import com.positiondev.cart.receipt.Receipt;
import com.positiondev.cart.util.PersistableMoney;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {
  @Test
  public void totalGetCopiedToReceipt() throws Exception {
    Cart cart = new Cart();
    cart.addProduct(new Product("Test product", "", PersistableMoney.usd(99)));
    cart.addProduct(new Product("Test product2", "", PersistableMoney.usd(51)));

    Receipt receipt = cart.receipt();
    assertEquals(PersistableMoney.usd(150), receipt.getTotal());

    cart.addProduct(new Product("Test product2", "", PersistableMoney.usd(51)));

    assertEquals(PersistableMoney.usd(150), receipt.getTotal());
  }

  @Test
  public void getTotal() throws Exception {
    Cart cart = new Cart();
    cart.addProduct(new Product("Test product", "", PersistableMoney.usd(99)));
    cart.addProduct(new Product("Test product2", "", PersistableMoney.usd(51)));

    assertEquals(PersistableMoney.usd(150), cart.getTotal());
  }
}