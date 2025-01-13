package org.jgrs.shoppingcart.promotion;

import lombok.RequiredArgsConstructor;
import org.jgrs.shoppingcart.cart.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final List<PriceCalculator> priceCalculators;

    public BigDecimal calculateDiscount(Cart cart) {
        return priceCalculators.stream()
                .map(pc -> pc.calculateDiscount(cart))
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calculateTotalPrice(Cart sale) {
        return priceCalculators.stream()
                .map(pc -> pc.calculateTotalPrice(sale))
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calculatePriceBeforeDiscount(Cart sale) {
        return priceCalculators.stream().findAny()
                .map(pc -> pc.calculatePriceBeforeDiscount(sale))
                .orElse(BigDecimal.ZERO);
    }

}
