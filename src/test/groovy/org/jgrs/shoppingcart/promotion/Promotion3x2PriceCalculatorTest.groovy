package org.jgrs.shoppingcart.promotion

import spock.lang.Specification
import spock.lang.Unroll

import static org.jgrs.shoppingcart.ObjectMother.buildCart
import static org.jgrs.shoppingcart.ObjectMother.buildProductPrice

class Promotion3x2PriceCalculatorTest extends Specification {

    @Unroll
    def "Calculate Total price #scenario"() {
        given:
        def promotion3x2PriceCalculator = new Promotion3x2PriceCalculator()
        def cart = buildCart(
                items: items
        )

        when:
        def totalPrice = promotion3x2PriceCalculator.calculateTotalPrice(cart)

        then:
        totalPrice == totalPriceExpected

        where:
        scenario                | totalPriceExpected    | items
        "3 t-shirt"             | 71.98                 | Map.of(buildProductPrice(), 3)
        "2 t-shirt 2 jeans"     | 166.99                | Map.of(buildProductPrice(), 2, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 2)
        "3 dresses"             | 161.50                | Map.of(buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), 3)
        "2 dresses 2 jeans"     | 227.00                | Map.of(buildProductPrice(product: "Dress", price: BigDecimal.valueOf(80.75)), 2, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 2)
        "4 t-shirts 1 jeans"    | 173.47                | Map.of(buildProductPrice(), 4, buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), 1)
        "less than 3"           | 71.98                 | Map.of(buildProductPrice(), 2)
        "single item"           | 80.75                 | Map.of(buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), 1)
        "empty"                 | 0.00                  | new HashMap<>()
    }

}
