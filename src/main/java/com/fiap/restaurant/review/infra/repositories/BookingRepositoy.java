package com.fiap.restaurant.review.infra.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.BookingModel;

@Repository
public interface BookingRepositoy extends JpaRepository<BookingModel, Long> {

    @Query(" SELECT booking FROM BookingModel booking "
            + " WHERE booking.tables.available = false "
            + " AND booking.tables.restaurant.cnpj = :cnpj "
            + " AND booking.canceled = false "
            + " AND booking.reservedDate = :reservedDate ")
    List<BookingModel> findAllBookingsWithTable(
        @Param("cnpj") String cnpj,
        @Param("reservedDate") LocalDateTime reservedDate);

}
