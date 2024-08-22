package com.fiap.restaurant.review.application.records.user;

import com.fiap.restaurant.review.infra.models.User;

public record UserRecord(
        String cpf,
        String phone,
        String fullname,
        String username,
        String password) {

    public static User toEntity(final UserRecord record) {
        final var entity = new User();
        entity.setCpf(record.cpf);
        entity.setPhone(record.phone);
        entity.setFullName(record.fullname);
        entity.setUsername(record.username);
        entity.setPassword(record.password);
        return entity;
    }

    public static UserRecord toRecord(final User entity) {
        return new UserRecord(
                entity.getCpf(),
                entity.getPhone(),
                entity.getFullName(),
                entity.getUsername(),
                entity.getPassword());
    }

}
