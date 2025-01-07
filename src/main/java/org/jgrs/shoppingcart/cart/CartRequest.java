package org.jgrs.shoppingcart.cart;

import lombok.Data;
import org.jgrs.shoppingcart.customer.Customer;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartRequest {

    private List<CartItem> items = new ArrayList<>();
    private Customer customer;

}
