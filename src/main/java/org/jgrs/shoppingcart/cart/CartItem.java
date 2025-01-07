package org.jgrs.shoppingcart.cart;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.jgrs.shoppingcart.product.ProductPrice;

@Data
@Entity
public class CartItem {

    @Id
    private Integer id;
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
