package com.fiap.restaurant.review.infra.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingModel {

    @Id
    @Column(name = "id_booking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity_people")
    private Integer quantityPeople;

    @Column(name = "reserved_date")
    private LocalDateTime reservedDate;
    
    @Column(name = "canceled")
    private Boolean canceled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users", foreignKey = @ForeignKey(name = "fk_booking_users"))
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tables", foreignKey = @ForeignKey(name = "fk_bookings_tables"))
    private TableModel tables;

}
