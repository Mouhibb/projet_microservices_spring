package com.ms.player.dto;

import lombok.Builder;

@Builder
public record DTOPlayer(String name, long price, TeamDto teamDto){ }
