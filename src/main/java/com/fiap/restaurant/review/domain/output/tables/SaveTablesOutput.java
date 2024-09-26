package com.fiap.restaurant.review.domain.output.tables;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.*;

@Data
@Getter
@Setter
public class SaveTablesOutput implements OutputInterface {

    private final TableEntity tableEnity;
    private final OutputStatus outputStatus;

    @Override
    public Object getBody() {
        return this.tableEnity;
    }

}
