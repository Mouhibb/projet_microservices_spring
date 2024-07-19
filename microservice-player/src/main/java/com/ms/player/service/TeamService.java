package com.ms.player.service;

import  com.ms.player.dto.TeamDto;
import com.ms.player.feign.ITeamClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TeamService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ITeamClient teamClient;



    private static final String TEAM_SERVICE_URL = "http://localhost:8080/team/";

    @CircuitBreaker(name = "teamServiceCircuitBreaker", fallbackMethod = "getDefaultTeam")
    public TeamDto getTeamByIdRest(String id) {
        return restTemplate.getForObject(TEAM_SERVICE_URL + id, TeamDto.class);
    }
    @CircuitBreaker(name = "teamCircuitBreaker", fallbackMethod = "getDefaultTeam")
    public TeamDto getTeamById(String id) {
        return teamClient.getTeamById(id);
    }
    public TeamDto getDefaultTeam(String id, Throwable throwable) {
        return new TeamDto(id,"team is not Available");

    }
}
