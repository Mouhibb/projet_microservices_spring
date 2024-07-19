package com.ms.player.feign;

import com.ms.player.dto.TeamDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TEAMSERVICE/team")
public interface ITeamClient {
    @GetMapping("/{id}")
    TeamDto getTeamById(@PathVariable("id") String id);
}