package com.ms.player.dto;

import lombok.Builder;

@Builder
public record PlayerDTO(String name, long price, String team) {
    }
