package org.jgrs.shoppingcart.promotion

import org.jgrs.shoppingcart.product.ProductPrice
import org.jgrs.shoppingcart.sale.Cart
import spock.lang.Specification

import static org.jgrs.shoppingcart.ObjectMother.buildCart

class Promotion3x2PriceCalculatorTest extends Specification {

    def "CalculateDiscount"() {
        given:
        def promotion3x2PriceCalculator = new Promotion3x2PriceCalculator()
        def cart = buildCart()

        when:
        def totalPrice = promotion3x2PriceCalculator.calculateTotalPrice(cart)

        then:
        totalPrice == 40.0
    }

}
