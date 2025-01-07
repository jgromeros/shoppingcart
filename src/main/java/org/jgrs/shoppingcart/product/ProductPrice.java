package org.jgrs.shoppingcart.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductPrice {

    @Id
    @Column(name = "product_price_id")
    private Integer id;
    private String product;
    private BigDecimal price;

}
