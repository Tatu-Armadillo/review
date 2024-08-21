package com.fiap.restaurant.review.model;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @Column(name = "id_booking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity_people")
    private Integer quantityPeople;

    @Column(name = "reserved_date")
    private LocalDateTime reservedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users", foreignKey = @ForeignKey(name = "fk_booking_users"))
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tables_bookings", joinColumns = { @JoinColumn(name = "id_table") }, inverseJoinColumns = { @JoinColumn(name = "id_booking") })
    private Set<Tables> bookings;

}
