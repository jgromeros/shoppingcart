package org.jgrs.shoppingcart

import org.jgrs.shoppingcart.customer.Customer
import org.jgrs.shoppingcart.customer.CustomerType
import org.jgrs.shoppingcart.product.ProductPrice
import org.jgrs.shoppingcart.sale.Cart

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
                product: "T-shirt",
                price: BigDecimal.valueOf(35.99)
        ))
    }

    static Customer buildCustomer(props = null) {
        applyProperties(props, new Customer(
                id: "John Doe",
                customerType: CustomerType.VIP
        ))
    }

    static <T> T applyProperties(props, T object) {
        if (props) {
            props.each { k, v -> object[k] = v }
        }
        object
    }

}
