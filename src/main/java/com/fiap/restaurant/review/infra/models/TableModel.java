package com.fiap.restaurant.review.infra.models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tables")
@Data
public class TableModel {

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
    private RestaurantModel restaurant;

    @OneToMany(mappedBy = "tables", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingModel> bookings;

}
