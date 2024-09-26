package com.fiap.restaurant.review.domain.gateway.tables;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;

public interface SaveTableInterface {
    void saveTables(final TableEntity tableEntity);
}
