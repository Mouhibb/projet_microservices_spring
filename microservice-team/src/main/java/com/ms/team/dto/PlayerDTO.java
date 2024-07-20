package com.ms.team.dto;

import lombok.Builder;

@Builder
public record PlayerDTO(String name, long number, String team) {
    }