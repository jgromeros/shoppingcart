package org.jgrs.shoppingcart.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @Column(name = "customer_id")
    private String id;
    private CustomerType customerType;

}
