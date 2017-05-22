package com.positiondev.cart.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/lineItems/{id}")
public class LineItemController {
  @Autowired private LineItemRepository lineItemRepository;

  @GetMapping("/destroy")
  public RedirectView lineItem(@PathVariable("id") LineItem lineItem) {
    Long cartId = lineItem.getCart().getId();
    lineItemRepository.delete(lineItem);
    return new RedirectView("/carts/" + cartId);
  }
}