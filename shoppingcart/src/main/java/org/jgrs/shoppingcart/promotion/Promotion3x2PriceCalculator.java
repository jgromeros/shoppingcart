package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.cart.CartItem;
import org.jgrs.shoppingcart.product.ProductPrice;
import org.jgrs.shoppingcart.cart.Cart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Promotion3x2PriceCalculator implements PriceCalculator {
    @Override
    public BigDecimal calculateDiscount(Cart sale) {
        Integer totalQuantity = sale.getItems()
                .stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
        if (totalQuantity < 3) {
            return BigDecimal.ZERO;
        }
        return sale.getItems().stream()
                .min((ci1, ci2) -> ci1.getProductPrice().getPrice().compareTo(ci2.getProductPrice().getPrice()))
                .map(CartItem::getProductPrice)
                .map(ProductPrice::getPrice)
                .orElse(BigDecimal.ZERO);
    }

}
