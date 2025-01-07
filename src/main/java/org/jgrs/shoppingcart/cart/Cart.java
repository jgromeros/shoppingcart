package org.jgrs.shoppingcart.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jgrs.shoppingcart.customer.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @Column(name = "cart_id")
    private Long id;
    @OneToMany(mappedBy = "cart")
    @Builder.Default
    private List<CartItem> items = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private BigDecimal totalBeforeDiscount;
    private BigDecimal discount;
    private BigDecimal total;

}
