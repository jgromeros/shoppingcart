package org.jgrs.shoppingcart.promotion

import spock.lang.Specification
import spock.lang.Unroll

import static org.jgrs.shoppingcart.ObjectMother.buildCart
import static org.jgrs.shoppingcart.ObjectMother.buildCartItem
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
        "3 t-shirt"             | 71.98                 | Arrays.asList(buildCartItem(quantity: 3))
        "2 t-shirt 2 jeans"     | 166.99                | Arrays.asList(buildCartItem(productPrice: buildProductPrice(), quantity: 2), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 2))
        "3 dresses"             | 161.50                | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), quantity: 3))
        "2 dresses 2 jeans"     | 227.00                | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress", price: BigDecimal.valueOf(80.75)), quantity: 2), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 2))
        "4 t-shirts 1 jeans"    | 173.47                | Arrays.asList(buildCartItem(productPrice: buildProductPrice(), quantity: 4), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 1))
        "less than 3"           | 71.98                 | Arrays.asList(buildCartItem())
        "single item"           | 80.75                 | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), quantity: 1))
        "empty"                 | 0.00                  | new ArrayList<>()
        "null"                  | 0.00                  | null
    }

}
