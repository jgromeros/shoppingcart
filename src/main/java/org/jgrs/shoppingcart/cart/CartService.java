package org.jgrs.shoppingcart.cart;

import org.jgrs.shoppingcart.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    public Cart createCart(Customer customer, List<CartItem> items) {
        Cart cart = Cart.builder()
                .customer(customer)
                .items(items)
                .build();
        return cart;
    }
}
