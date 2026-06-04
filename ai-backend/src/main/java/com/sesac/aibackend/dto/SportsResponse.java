package com.sesac.aibackend.dto;

import com.sesac.aibackend.domain.Sports;

public record SportsResponse(
        Long id,
        String name,
        String category,
        int playerCount,
        boolean indoor
) {

    public static SportsResponse from(Sports sports) {
        return new SportsResponse(
                sports.getId(),
                sports.getName(),
                sports.getCategory(),
                sports.getPlayerCount(),
                sports.isIndoor()
        );
    }
}