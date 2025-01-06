package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.product.ProductPrice;
import org.jgrs.shoppingcart.sale.Cart;

import java.math.BigDecimal;

public class Promotion3x2PriceCalculator implements PriceCalculator {
    @Override
    public BigDecimal calculateDiscount(Cart sale) {
        if (sale.getItems().size() < 3) {
            return BigDecimal.ZERO;
        }
        return sale.getItems().keySet().stream()
                .min((pp1, pp2) -> pp1.getPrice().compareTo(pp2.getPrice()))
                .map(ProductPrice::getPrice)
                .orElse(BigDecimal.ZERO);
    }

}
