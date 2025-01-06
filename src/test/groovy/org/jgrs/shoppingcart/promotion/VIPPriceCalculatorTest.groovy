package org.jgrs.shoppingcart.promotion

import spock.lang.Specification

import static org.jgrs.shoppingcart.ObjectMother.buildCart
import static org.jgrs.shoppingcart.ObjectMother.buildCustomer
import static org.jgrs.shoppingcart.ObjectMother.buildProductPrice

class VIPPriceCalculatorTest extends Specification {

    def "Calculate Price #scenario"() {
        given:
        def vipPriceCalculator = new VIPPriceCalculator()
        def cart = buildCart(
                items: items,
                customer: customer
        )

        when:
        def totalPrice = vipPriceCalculator.calculateTotalPrice(cart)

        then:
        totalPrice == totalPriceExpected

        where:
        scenario                | totalPriceExpected    | customer          | items
        "3 t-shirt"             | 91.77                 | buildCustomer()   | Map.of(buildProductPrice(), 3)
        "2 t-shirt 2 jeans"     | 172.53                | buildCustomer()   | Map.of(buildProductPrice(), 2, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 2)
        "3 dresses"             | 205.91                | buildCustomer()   | Map.of(buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), 3)
        "2 dresses 2 jeans"     | 248.62                | buildCustomer()   | Map.of(buildProductPrice(product: "Dress", price: BigDecimal.valueOf(80.75)), 2, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 2)
        "4 t-shirts 1 jeans"    | 178.04                | buildCustomer()   | Map.of(buildProductPrice(), 4, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 1)
        "less than 3"           | 61.18                 | buildCustomer()   | Map.of(buildProductPrice(), 2)
        "single item"           | 68.64                 | buildCustomer()   | Map.of(buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), 1)
        "empty"                 | 0.00                  | buildCustomer()   | new HashMap<>()
        "null"                  | 0.00                  | buildCustomer()   | null

    }

}
