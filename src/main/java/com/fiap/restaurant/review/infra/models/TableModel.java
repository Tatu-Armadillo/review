package com.fiap.restaurant.review.infra.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
