package com.ms.player.dto;

import lombok.Builder;

@Builder
public record PlayerDTO(String name, long number, String team) {
    }
