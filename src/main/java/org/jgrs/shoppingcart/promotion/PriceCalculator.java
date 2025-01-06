package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.cart.Cart;

import java.math.BigDecimal;

public interface PriceCalculator {

    default BigDecimal calculatePriceBeforeDiscount(Cart sale) {
        return sale.getItems().entrySet().stream()
                .map(entry ->
                        entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal calculateDiscount(Cart sale);

    default BigDecimal calculateTotalPrice(Cart sale) {
        if (sale.getItems() == null || sale.getItems().isEmpty()) {
            return BigDecimal.ZERO;
        }
        return calculatePriceBeforeDiscount(sale).subtract(calculateDiscount(sale));
    }

}
