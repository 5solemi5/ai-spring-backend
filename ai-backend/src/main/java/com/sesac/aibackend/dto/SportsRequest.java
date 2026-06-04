package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Sports;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SportsRequest(
        @NotBlank String name,
        @NotBlank String category,
        @Min(1) int playerCount,
        boolean indoor
) {

    public Sports toEntity() {
        return Sports.builder()
                .name(name)
                .category(category)
                .playerCount(playerCount)
                .indoor(indoor)
                .build();
    }
}