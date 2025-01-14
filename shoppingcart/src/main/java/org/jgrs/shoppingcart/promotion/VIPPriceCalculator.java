package org.jgrs.shoppingcart.promotion;

import lombok.extern.slf4j.Slf4j;
import org.jgrs.shoppingcart.customer.Customer;
import org.jgrs.shoppingcart.customer.CustomerType;
import org.jgrs.shoppingcart.cart.Cart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Component
public class VIPPriceCalculator implements PriceCalculator {

    private static final BigDecimal VIP_DISCOUNT = new BigDecimal("0.15");

    @Override
    public BigDecimal calculateDiscount(Cart sale) {
        if (!CustomerType.VIP.equals(Optional.ofNullable(sale)
                .map(Cart::getCustomer)
                .map(Customer::getCustomerType)
                .orElse(CustomerType.COMMON))) {
            log.info("COMMON customer can't use VIP promotion");
            return BigDecimal.ZERO;
        }
        return calculatePriceBeforeDiscount(sale).multiply(VIP_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    }

}
