package org.jgrs.shoppingcart.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jgrs.shoppingcart.customer.Customer;
import org.jgrs.shoppingcart.product.ProductPrice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Builder.Default
    private Map<ProductPrice, Integer> items = new HashMap<>();
    private Customer customer;
    private BigDecimal totalBeforeDiscount;
    private BigDecimal discount;
    private BigDecimal total;

    public void addProduct(ProductPrice product) {
        items.put(product, items.get(product) != null ? items.get(product) + 1 : 1);
    }

    public void removeProduct(ProductPrice product) {
        if (items.get(product) == 1) {
            items.remove(product);
        } else {
            items.put(product, items.get(product) - 1);
        }
    }

}
