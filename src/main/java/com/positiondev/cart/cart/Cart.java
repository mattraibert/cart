package com.positiondev.cart.cart;

import com.google.common.collect.Lists;
import com.positiondev.cart.product.Product;
import com.positiondev.cart.util.PersistableMoney;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    return PersistableMoney.sum(getProducts().stream().map(Product::getPrice).collect(Collectors.toList()));
  }

  public List<Product> getProducts() {
    return Lists.transform(lineItems, LineItem::getProduct);
  }

  Optional<Charge> charge(@RequestParam(value = "stripeToken") String token) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
    Map<String, Object> params = new HashMap<>();
    int amountCents = getTotal().getAmountCents();
    if (amountCents > 0) {
      params.put("amount", amountCents);
      params.put("currency", getTotal().getCurrency());
      params.put("description", "Position Cart #" + getId());
      params.put("source", token);

      return Optional.of(Charge.create(params));
    }
    return Optional.empty();
  }
}