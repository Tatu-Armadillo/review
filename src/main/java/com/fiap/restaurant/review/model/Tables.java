package com.fiap.restaurant.review.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tables")
@Data
public class Tables {

    @Id
    @Column(name = "id_table")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", foreignKey = @ForeignKey(name = "fk_table_restaurant"))
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tables_bookings", joinColumns = { @JoinColumn(name = "id_booking") }, inverseJoinColumns = { @JoinColumn(name = "id_table") })
    private Set<Booking> bookings;

}
