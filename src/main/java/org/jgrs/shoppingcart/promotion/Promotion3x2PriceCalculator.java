package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.product.ProductPrice;
import org.jgrs.shoppingcart.sale.Cart;

import java.math.BigDecimal;

public class Promotion3x2PriceCalculator implements PriceCalculator {
    @Override
    public BigDecimal calculateDiscount(Cart sale) {
        Integer totalQuantity = sale.getItems().values().stream()
                .reduce(0, Integer::sum);
        if (totalQuantity < 3) {
            return BigDecimal.ZERO;
        }
        return sale.getItems().keySet().stream()
                .min((pp1, pp2) -> pp1.getPrice().compareTo(pp2.getPrice()))
                .map(ProductPrice::getPrice)
                .orElse(BigDecimal.ZERO);
    }

}
