package com.fiap.restaurant.review.domain.output.tables;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class FindAllTablesOutput implements OutputInterface {

    private final List<TableEntity> tablesEntities;
    private final OutputStatus outputStatus;

    @Override
    public Object getBody() {
        return this.tablesEntities;
    }

}
