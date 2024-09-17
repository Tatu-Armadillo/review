package com.fiap.restaurant.review.infra.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bookings")
@Data
public class BookingModel {

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
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tables", foreignKey = @ForeignKey(name = "fk_bookings_tables"))
    private TableModel tables;

}
