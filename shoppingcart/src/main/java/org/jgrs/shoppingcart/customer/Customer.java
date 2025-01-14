package org.jgrs.shoppingcart.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    private CustomerType customerType;

    @JsonCreator
    public Customer(String customerURIString) {
        String idString = customerURIString.substring(customerURIString.lastIndexOf("/") + 1);
        this.id = Integer.parseInt(idString);
    }

}
