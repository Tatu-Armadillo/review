package com.fiap.restaurant.review.records;

import com.fiap.restaurant.review.model.Address;

public record AddressRecord(
        String cep,
        String publicPlace,
        String complement,
        String neighborhood,
        String city,
        String ufState,
        Double latitude,
        Double longitude) {

    public static Address toEntity(final AddressRecord record) {
        final var entity = new Address();
        entity.setCep(record.cep);
        entity.setPublicPlace(record.publicPlace);
        entity.setComplement(record.complement);
        entity.setNeighborhood(record.neighborhood);
        entity.setCity(record.city);
        entity.setUfState(record.ufState);
        entity.setLatitude(record.latitude);
        entity.setLongitude(record.longitude);
        return entity;
    }

    public static AddressRecord toRecord(final Address entity) {
        return new AddressRecord(
                entity.getCep(),
                entity.getPublicPlace(),
                entity.getComplement(),
                entity.getNeighborhood(),
                entity.getCity(),
                entity.getUfState(),
                entity.getLatitude(),
                entity.getLongitude());
    }

}