package com.fiap.restaurant.review.domain.generic.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserOutput implements OutputInterface {

    private final Long id;
    private final String cpf;
    private final String phone;
    private final String username;
    private final String fullName;
    private final String password;

    @Override
    public Object getBody() {
        return this;
    }

    @Override
    public OutputStatus getOutputStatus() {
        return new OutputStatus(200, "Ok", "Cliente encontrado");
    }

}
