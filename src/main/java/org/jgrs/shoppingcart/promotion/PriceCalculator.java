package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.sale.Cart;

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
        return calculatePriceBeforeDiscount(sale).subtract(calculateDiscount(sale));
    }

}
