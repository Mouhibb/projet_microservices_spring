package com.ms.team.service;

import com.ms.team.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAll();
    Optional<Team> findById(String id);
    Team save(Team team);
    void deleteById(String id);
}
