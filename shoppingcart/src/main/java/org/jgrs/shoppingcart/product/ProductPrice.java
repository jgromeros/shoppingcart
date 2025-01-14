package org.jgrs.shoppingcart.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_price_id")
    private Integer id;
    @EqualsAndHashCode.Exclude
    private String product;
    @EqualsAndHashCode.Exclude
    private BigDecimal price;

    @JsonCreator
    public ProductPrice(String productURIString) {
        String idString = productURIString.substring(productURIString.lastIndexOf("/") + 1);
        this.id = Integer.parseInt(idString);
    }

}
