package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacations")
@Getter
@Setter

public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "vacation_title", nullable = false)
    @JsonProperty("vacation_title")
    private String vacation_title;

    @Column(name = "description", nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(name = "travel_fare_price", nullable = false)
    @JsonProperty("travel_price")
    private BigDecimal travel_price;

    @Column(name = "image_url", nullable = false)
    @JsonProperty("image_URL")
    private String image_URL;

    @Column(name = "create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "last_update")
    @UpdateTimestamp
    private Date last_update;

    @OneToMany(mappedBy = "vacation")
    private Set<Excursion> excursions = new HashSet<>();
}
