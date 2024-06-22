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
@Table(name = "excursions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "excursion_title", nullable = false)
    @JsonProperty("excursion_title")
    private String excursion_title;

    @Column(name = "excursion_price", nullable = false)
    @JsonProperty("excursion_price")
    private BigDecimal excursion_price;

    @Column(name = "image_url", nullable = false)
    @JsonProperty("image_URL")
    private String image_URL;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacation vacation;


    @ManyToMany(mappedBy = "excursions", cascade = CascadeType.ALL)
    private Set<CartItem> cart_items;

    /*
    @ManyToMany
    @JoinTable(name="excursion_cartitem",
            joinColumns=@JoinColumn(name="excursion_id"),
            inverseJoinColumns=@JoinColumn(name="cart_item_id"))
    private Set<CartItem> cartitems = new HashSet<>();
*/
}

