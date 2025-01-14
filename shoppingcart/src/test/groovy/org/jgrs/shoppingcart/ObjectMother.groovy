package org.jgrs.shoppingcart

import org.jgrs.shoppingcart.cart.CartItem
import org.jgrs.shoppingcart.customer.Customer
import org.jgrs.shoppingcart.customer.CustomerType
import org.jgrs.shoppingcart.product.ProductPrice
import org.jgrs.shoppingcart.cart.Cart

class ObjectMother {

    static Cart buildCart(props = null) {
        applyProperties(props, new Cart(
            items: Arrays.asList(buildCartItem())
        ))
    }

    static CartItem buildCartItem(props = null) {
        applyProperties(props, new CartItem(
                productPrice: buildProductPrice(),
                quantity: 2
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
                id: 123,
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
