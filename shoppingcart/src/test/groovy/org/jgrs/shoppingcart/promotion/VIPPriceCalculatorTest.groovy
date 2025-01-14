package org.jgrs.shoppingcart.promotion

import spock.lang.Specification

import static org.jgrs.shoppingcart.ObjectMother.buildCart
import static org.jgrs.shoppingcart.ObjectMother.buildCartItem
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
        "3 t-shirt"             | 91.77                 | buildCustomer()   | Arrays.asList(buildCartItem(quantity: 3))
        "2 t-shirt 2 jeans"     | 172.53                | buildCustomer()   | Arrays.asList(buildCartItem(productPrice: buildProductPrice(), quantity: 2), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 2))
        "3 dresses"             | 205.91                | buildCustomer()   | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), quantity: 3))
        "2 dresses 2 jeans"     | 248.62                | buildCustomer()   | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress", price: BigDecimal.valueOf(80.75)), quantity: 2), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 2))
        "4 t-shirts 1 jeans"    | 178.04                | buildCustomer()   | Arrays.asList(buildCartItem(productPrice: buildProductPrice(), quantity: 4), buildCartItem(productPrice: buildProductPrice(product: "Jeans",price: BigDecimal.valueOf(65.50)), quantity: 1))
        "less than 3"           | 61.18                 | buildCustomer()   | Arrays.asList(buildCartItem())
        "single item"           | 68.64                 | buildCustomer()   | Arrays.asList(buildCartItem(productPrice: buildProductPrice(product: "Dress",price: BigDecimal.valueOf(80.75)), quantity: 1))
        "empty"                 | 0.00                  | buildCustomer()   | new ArrayList<>()
        "null"                  | 0.00                  | buildCustomer()   | null

    }

}
