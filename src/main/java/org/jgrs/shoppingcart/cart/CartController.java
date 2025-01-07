package org.jgrs.shoppingcart.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jgrs.shoppingcart.customer.Customer;
import org.jgrs.shoppingcart.product.ProductPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping(value = "/api/cart")
    public ResponseEntity<Cart> createCart(@RequestBody CartRequest cartRequest, HttpServletRequest request) {
        Cart cart = cartService.createCart(cartRequest.getCustomer(), cartRequest.getItems());
        URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                .path("/{id}")
                .buildAndExpand(cart.getId())
                .toUri();
        return ResponseEntity.created(location).body(cart);
    }
//
//    @PutMapping(value = "/api/cart")
//    public ResponseEntity<Cart> createCart(@RequestBody CartRequest cartRequest) {
//        Cart cart = cartService.createCart(cartRequest.getCustomer(), cartRequest.getItems());
//        return ResponseEntity.created(location).body(cart);
//    }

}
