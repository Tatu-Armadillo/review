package com.fiap.restaurant.review.domain.entities.booking;

import java.time.LocalDateTime;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.entities.user.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingsEntity {
    private Long id;
    private Integer quantityPeople;
    private LocalDateTime reservedDate;
    private boolean canceled;
    private UserEntity userEntity;
    private TableEntity tableEntity;

    public BookingsEntity(
            Integer quantityPeople,
            LocalDateTime reservedDate,
            boolean canceled,
            UserEntity userEntity,
            TableEntity tableEntity) {
        this.quantityPeople = quantityPeople;
        this.reservedDate = reservedDate;
        this.canceled = canceled;
        this.userEntity = userEntity;
        this.tableEntity = tableEntity;
    }

}
