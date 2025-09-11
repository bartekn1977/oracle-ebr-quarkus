package org.acme.dto;

public record PetDto(
        String name,
        String tags,
        String status,
        String category
) {
}
