package org.jgrs.shoppingcart

import org.jgrs.shoppingcart.product.ProductPrice
import org.jgrs.shoppingcart.sale.Cart

import java.util.stream.Collectors
import java.util.stream.Stream

class ObjectMother {

    static Cart buildCart(props = null) {
        applyProperties(props, new Cart(
            items: Map.of(buildProductPrice(), 2,
                    buildProductPrice(
                        product: "Product2",
                        price: BigDecimal.valueOf(19.0)), 1)
        ))
    }

    static ProductPrice buildProductPrice(props = null) {
        applyProperties(props, new ProductPrice(
                product: "Product1",
                price: BigDecimal.valueOf(20.0)
        ))
    }

    static <T> T applyProperties(props, T object) {
        if (props) {
            props.each { k, v -> object[k] = v }
        }
        object
    }

}
