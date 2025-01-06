package org.jgrs.shoppingcart.promotion;

import org.jgrs.shoppingcart.customer.Customer;
import org.jgrs.shoppingcart.customer.CustomerType;
import org.jgrs.shoppingcart.sale.Cart;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

public class VIPPriceCalculator implements PriceCalculator {

    private static final BigDecimal VIP_DISCOUNT = new BigDecimal("0.15");

    @Override
    public BigDecimal calculateDiscount(Cart sale) {
        if (!CustomerType.VIP.equals(Optional.ofNullable(sale)
                .map(Cart::getCustomer)
                .map(Customer::getCustomerType)
                .orElse(CustomerType.COMMON))) {
            throw new IllegalArgumentException("COMMON customer can't use this promotion");
        }
        return calculatePriceBeforeDiscount(sale).multiply(VIP_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    }

}
