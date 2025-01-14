package org.jgrs.shoppingcart.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartUpdateRequest {

    private List<CartItem> items;

}
