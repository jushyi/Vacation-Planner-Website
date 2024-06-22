package com.example.demo.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "order_tracking_number", nullable = false)
    private String orderTrackingNumber;

    @Column(name = "package_price", nullable = false)
    @JsonProperty("package_price")
    private BigDecimal package_price;

    @Column(name = "party_size", nullable = false)
    @JsonProperty("party_size")
    private int party_size;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonProperty("status")
    private StatusType status;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonProperty("customer")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> cart_items = new HashSet<>();

    public void add(CartItem item) {

        if (item != null) {
            if (cart_items == null) {
                cart_items = new HashSet<>();
            }
            cart_items.add(item);
            item.setCart(this);
        }
    }
}

